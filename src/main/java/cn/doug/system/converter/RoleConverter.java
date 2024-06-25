package cn.doug.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysRole;
import cn.doug.system.model.form.sys.RoleForm;
import cn.doug.system.model.vo.sys.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author pengqihai
 * @since 2022/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> entity2Page(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);

    RoleForm entity2Form(SysRole entity);
}