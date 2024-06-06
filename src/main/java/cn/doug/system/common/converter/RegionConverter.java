package cn.doug.system.common.converter;

import cn.doug.system.model.entity.SysMenu;
import cn.doug.system.model.vo.MenuVO;
import cn.doug.system.model.vo.RegionVO;
import cn.doug.system.model.vo.RouteVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.dto.RegionDTO;
import cn.doug.system.model.entity.Region;
import cn.doug.system.model.vo.RegionPageVO;
import cn.doug.system.model.form.RegionForm;
import cn.doug.system.model.bo.RegionBO;

/**
 * 地区表转换器
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Mapper(componentModel = "spring")
public interface RegionConverter{

    RegionVO entity2Vo(Region entity);

    RegionPageVO bo2PageVo(RegionBO bo);

    Page<RegionPageVO> bo2PageVo(Page<RegionBO> bo);

    RegionForm entity2Form(Region entity);

    @InheritInverseConfiguration(name = "entity2Form")
    Region form2Entity(RegionForm entity);
}