package cn.doug.system.converter;

import cn.doug.system.model.entity.SysMenu;
import cn.doug.system.model.form.sys.SysMenuForm;
import cn.doug.system.model.vo.sys.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 * @author pengqihai
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO entity2Vo(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenuForm convertToForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu convertToEntity(SysMenuForm menuForm);

}