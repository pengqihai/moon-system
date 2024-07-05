package cn.doug.system.plugin.easyexcel;

import cn.doug.common.base.IBaseEnum;
import cn.doug.common.enums.GenderEnum;
import cn.doug.system.common.enums.MarryEnum;
import cn.doug.system.converter.VeteranCadreConverter;
import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.model.vo.digit.VeteranCadreImportVO;
import cn.doug.system.service.VeteranCadreService;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.log4j.Log4j2;


/**
 * @Author: pengqihai
 * @Date: 2024/07/04/15:12
 * @Description: 离退休老干部信息
 */
@Log4j2
public class VeteranCadreListener extends MyAnalysisEventListener<VeteranCadreImportVO>{

    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    final VeteranCadreService veteranCadreService;

    final VeteranCadreConverter veteranCadreConverter;

    public VeteranCadreListener() {
        this.veteranCadreService = SpringUtil.getBean(VeteranCadreService.class);
        this.veteranCadreConverter = SpringUtil.getBean(VeteranCadreConverter.class);
    }

    /**
     * 每一条数据解析都会来调用
     * <p>
     * 1. 数据校验；全字段校验
     * 2. 数据持久化；
     *
     * @param importVO        一行数据，类似于 {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(VeteranCadreImportVO importVO, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSONUtil.toJsonStr(importVO));
        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String fullName = importVO.getFullName();
        if (StrUtil.isBlank(fullName)) {
            validationMsg.append("姓名为空；");
        }

        String idCard = importVO.getIdCard();
        if (StrUtil.isBlank(idCard)) {
            validationMsg.append("身份证号为空；");
        } else {
            long count = veteranCadreService.count(new LambdaQueryWrapper<VeteranCadreEntity>().eq(VeteranCadreEntity::getIdCard, idCard));
            if (count > 0) {
                validationMsg.append("用户已存在；");
            }
        }

        String phone = importVO.getPhone();
        if (StrUtil.isBlank(phone)) {
            validationMsg.append("手机号码为空；");
        } else {
            if (!Validator.isMobile(phone)) {
                validationMsg.append("手机号码不正确；");
            }
        }

        if (validationMsg.isEmpty()) {
            // 校验通过，持久化至数据库
            VeteranCadreEntity entity = veteranCadreConverter.importVo2Entity(importVO);
            // 性别翻译
            String genderLabel = importVO.getGender();
            if (StrUtil.isNotBlank(genderLabel)) {
                Integer genderValue = (Integer) IBaseEnum.getValueByLabel(genderLabel, GenderEnum.class);
                entity.setGender(genderValue);
            }
            // 婚姻状况
            String marryFlag = importVO.getMarryFlag();
            if (StrUtil.isNotBlank(marryFlag)) {
                Integer marryValue = (Integer) IBaseEnum.getValueByLabel(marryFlag, MarryEnum.class);
                entity.setMarryFlag(marryValue);
            }
            // 人员类型
            String personnelType = importVO.getPersonnelType();
            if (StrUtil.isNotBlank(personnelType)) {
                if ("干部".equals(personnelType)) {
                    entity.setPersonnelType(0);
                } else {
                    entity.setPersonnelType(1);
                }
            }

            // 是否关工委老同志
            String isCommitte = importVO.getIsCommitte();
            if (StrUtil.isNotBlank(isCommitte)) {
                entity.setIsCommitte("是".equals(isCommitte));
            }

            boolean saveResult = veteranCadreService.save(entity);
            if (saveResult) {
                validCount++;
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
