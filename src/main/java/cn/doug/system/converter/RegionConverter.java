package cn.doug.system.converter;

import cn.doug.system.model.entity.SysRegion;
import cn.doug.system.model.vo.digit.RegionVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.vo.digit.RegionPageVO;
import cn.doug.system.model.form.digit.RegionForm;
import cn.doug.system.model.bo.RegionBO;

import java.util.List;

/**
 * 地区表转换器
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Mapper(componentModel = "spring")
public interface RegionConverter{

    RegionVO entity2Vo(SysRegion entity);

    RegionPageVO bo2PageVo(RegionBO bo);

    Page<RegionPageVO> bo2PageVo(Page<RegionBO> bo);

    RegionForm entity2Form(SysRegion entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysRegion form2Entity(RegionForm entity);

    List<RegionVO> entitys2VOS(List<SysRegion> regions);
}