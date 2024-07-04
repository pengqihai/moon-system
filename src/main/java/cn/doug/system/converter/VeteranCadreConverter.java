package cn.doug.system.converter;

import cn.doug.system.model.vo.digit.VeteranCadreExportVO;
import cn.doug.system.model.vo.digit.VeteranCadreImportVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.dto.VeteranCadreDTO;
import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.bo.VeteranCadreBO;

import java.util.List;

/**
 * 老干部工作人员与离退休党员	转换器
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Mapper(componentModel = "spring")
public interface VeteranCadreConverter{

    VeteranCadrePageVO bo2PageVo(VeteranCadreBO bo);

    Page<VeteranCadrePageVO> bo2PageVo(Page<VeteranCadreBO> bo);

    VeteranCadreForm entity2Form(VeteranCadreEntity entity);

    @InheritInverseConfiguration(name = "entity2Form")
    VeteranCadreEntity form2Entity(VeteranCadreForm entity);

    List<VeteranCadreExportVO> entity2ExportVOList(List<VeteranCadreEntity> entityList);

    VeteranCadreEntity importVo2Entity(VeteranCadreImportVO vo);
}