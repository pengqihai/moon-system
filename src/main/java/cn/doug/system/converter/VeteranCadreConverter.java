package cn.doug.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.dto.VeteranCadreDTO;
import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.bo.VeteranCadreBO;

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
}