package cn.doug.system.converter;

import cn.doug.system.model.vo.digit.VeteranCadreExportVO;
import cn.doug.system.model.vo.digit.VeteranCadreImportVO;
import cn.doug.system.model.vo.digit.VeteranCadrePageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.bo.VeteranCadreBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 老干部工作人员与离退休党员	转换器
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Mapper(componentModel = "spring")
public interface VeteranCadreConverter{

    @Mappings({
            @Mapping(target = "gender", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.doug.common.enums.GenderEnum.class))"),
            @Mapping(target = "marryFlag", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getMarryFlag(), cn.doug.system.common.enums.MarryEnum.class))"),
            @Mapping(target = "personnelType", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getPersonnelType(), cn.doug.system.common.enums.PersonnelTypeEnum.class))"),
            @Mapping(target = "committeDut", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getCommitteDut(), cn.doug.system.common.enums.DutiesTypeEnum.class))")
    })
    VeteranCadrePageVO boToVo(VeteranCadreBO bo);

    default Page<VeteranCadrePageVO> boPageToVoPage(Page<VeteranCadreBO> boPage) {
        List<VeteranCadrePageVO> voList = boPage.getRecords().stream()
                .map(this::boToVo)
                .collect(Collectors.toList());
        Page<VeteranCadrePageVO> voPage = new Page<>(boPage.getCurrent(), boPage.getSize(), boPage.getTotal());
        voPage.setRecords(voList);
        return voPage;
    }

    VeteranCadreForm entity2Form(VeteranCadreEntity entity);

    @InheritInverseConfiguration(name = "entity2Form")
    VeteranCadreEntity form2Entity(VeteranCadreForm entity);

    @Mappings({
            @Mapping(target = "gender", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.doug.common.enums.GenderEnum.class))"),
            @Mapping(target = "isCommitte", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getIsCommitte(), cn.doug.common.enums.BooleanTypeEnums.class))"),
            @Mapping(target = "relocation", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getRelocation(), cn.doug.common.enums.BooleanTypeEnums.class))"),
            @Mapping(target = "isDie", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getIsDie(), cn.doug.common.enums.BooleanTypeEnums.class))"),
            @Mapping(target = "marryFlag", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getMarryFlag(), cn.doug.system.common.enums.MarryEnum.class))"),
            @Mapping(target = "personnelType", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getPersonnelType(), cn.doug.system.common.enums.PersonnelTypeEnum.class))"),
            @Mapping(target = "committeDut", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getCommitteDut(), cn.doug.system.common.enums.DutiesTypeEnum.class))")
    })
    VeteranCadreExportVO entityToExportVO(VeteranCadreEntity bo);

    List<VeteranCadreExportVO> entity2ExportVOList(List<VeteranCadreEntity> entityList);

    VeteranCadreEntity importVo2Entity(VeteranCadreImportVO vo);

}