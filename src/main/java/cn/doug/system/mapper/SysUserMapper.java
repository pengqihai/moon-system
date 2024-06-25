package cn.doug.system.mapper;

import cn.doug.system.model.bo.SysUserBO;
import cn.doug.system.model.form.sys.SysUserForm;
import cn.doug.system.model.query.SysUserPageQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.dto.UserAuthInfo;
import cn.doug.system.model.entity.SysUser;
import cn.doug.system.model.vo.sys.UserExportVO;
import cn.doug.system.plugin.mybatis.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户持久层
 *
 * @author pengqihai
 * @since 2022/1/14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "u")
    Page<SysUserBO> listPagedUsers(Page<SysUserBO> page, SysUserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    SysUserForm getUserFormData(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    @DataPermission(deptAlias = "u")
    List<UserExportVO> listExportUsers(SysUserPageQuery queryParams);
}
