package cn.doug.system.plugin.easyexcel;

import cn.doug.common.base.IBaseEnum;
import cn.doug.common.enums.GenderEnum;
import cn.doug.common.enums.StatusEnum;
import cn.doug.system.converter.SysUserConverter;
import cn.doug.system.model.vo.SysUserImportVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.doug.system.common.constant.SystemConstants;
import cn.doug.system.model.entity.SysRole;
import cn.doug.system.model.entity.SysUser;
import cn.doug.system.model.entity.SysUserRole;
import cn.doug.system.service.SysRoleService;
import cn.doug.system.service.SysUserRoleService;
import cn.doug.system.service.SysUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户导入监听器
 * <p>
 *  <a href="https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read#%E6%9C%80%E7%AE%80%E5%8D%95%E7%9A%84%E8%AF%BB%E7%9A%84%E7%9B%91%E5%90%AC%E5%99%A8">最简单的读的监听器</a>
 *
 * @author pengqihai
 * @since 2022/4/10 20:49
 */
@Log4j2
public class UserImportListener extends MyAnalysisEventListener<SysUserImportVO> {


    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    // 部门ID
    private final Long deptId;

    private final SysUserService userService;

    private final PasswordEncoder passwordEncoder;

    private final SysUserConverter userConverter;

    private final SysRoleService roleService;

    private final SysUserRoleService userRoleService;

    public UserImportListener(Long deptId) {
        this.deptId = deptId;
        this.userService = SpringUtil.getBean(SysUserService.class);
        this.passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
        this.roleService = SpringUtil.getBean(SysRoleService.class);
        this.userRoleService = SpringUtil.getBean(SysUserRoleService.class);
        this.userConverter = SpringUtil.getBean(SysUserConverter.class);
    }

    /**
     * 每一条数据解析都会来调用
     * <p>
     * 1. 数据校验；全字段校验
     * 2. 数据持久化；
     *
     * @param userImportVO    一行数据，类似于 {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(SysUserImportVO userImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSONUtil.toJsonStr(userImportVO));
        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String username = userImportVO.getUsername();
        if (StrUtil.isBlank(username)) {
            validationMsg.append("用户名为空；");
        } else {
            long count = userService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
            if (count > 0) {
                validationMsg.append("用户名已存在；");
            }
        }

        String nickname = userImportVO.getNickname();
        if (StrUtil.isBlank(nickname)) {
            validationMsg.append("用户昵称为空；");
        }

        String mobile = userImportVO.getMobile();
        if (StrUtil.isBlank(mobile)) {
            validationMsg.append("手机号码为空；");
        } else {
            if (!Validator.isMobile(mobile)) {
                validationMsg.append("手机号码不正确；");
            }
        }

        if (validationMsg.isEmpty()) {
            // 校验通过，持久化至数据库
            SysUser entity = userConverter.importVo2Entity(userImportVO);
            entity.setDeptId(deptId);   // 部门
            entity.setPassword(passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD));   // 默认密码
            // 性别翻译
            String genderLabel = userImportVO.getGenderLabel();
            if (StrUtil.isNotBlank(genderLabel)) {
                Integer genderValue = (Integer) IBaseEnum.getValueByLabel(genderLabel, GenderEnum.class);
                entity.setGender(genderValue);
            }

            // 角色解析
            String roleCodes = userImportVO.getRoleCodes();
            List<Long> roleIds = null;
            if (StrUtil.isNotBlank(roleCodes)) {
                roleIds = roleService.list(
                                new LambdaQueryWrapper<SysRole>()
                                        .in(SysRole::getCode, roleCodes.split(","))
                                        .eq(SysRole::getStatus, StatusEnum.ENABLE.getValue())
                                        .select(SysRole::getId)
                        ).stream()
                        .map(SysRole::getId)
                        .collect(Collectors.toList());
            }


            boolean saveResult = userService.save(entity);
            if (saveResult) {
                validCount++;
                // 保存用户角色关联
                if (CollectionUtil.isNotEmpty(roleIds)) {
                    List<SysUserRole> userRoles = roleIds.stream()
                            .map(roleId -> new SysUserRole(entity.getId(), roleId))
                            .collect(Collectors.toList());
                    userRoleService.saveBatch(userRoles);
                }
            } else {
                invalidCount++;
                msg.append("第").append(validCount + invalidCount).append("行数据保存失败；<br/>");
            }
        } else {
            invalidCount++;
            msg.append("第" + (validCount + invalidCount) + "行数据校验失败：").append(validationMsg + "<br/>");
        }
    }


    /**
     * 所有数据解析完成会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    @Override
    public String getMsg() {
        // 总结信息
        String summaryMsg = StrUtil.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
        return summaryMsg;
    }
}
