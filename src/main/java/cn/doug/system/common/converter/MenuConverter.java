package cn.doug.system.common.converter;

import cn.doug.system.model.entity.SysMenu;
import cn.doug.system.model.form.MenuForm;
import cn.doug.system.model.vo.MenuVO;
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
    MenuForm convertToForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu convertToEntity(MenuForm menuForm);

}