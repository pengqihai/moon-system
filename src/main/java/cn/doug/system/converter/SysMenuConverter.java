package cn.doug.system.converter;

import cn.doug.system.model.entity.SysMenu;
import cn.doug.system.model.form.SysMenuForm;
import cn.doug.system.model.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 * @author pengqihai
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface SysMenuConverter {

    SysMenuVO entity2Vo(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenuForm convertToForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu convertToEntity(SysMenuForm menuForm);

}