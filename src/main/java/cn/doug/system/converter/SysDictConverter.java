package cn.doug.system.converter;

import cn.doug.system.model.form.SysDictForm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.entity.SysDict;
import cn.doug.system.model.vo.SysDictPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @author pengqihai
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface SysDictConverter {

    Page<SysDictPageVO> entity2Page(Page<SysDict> page);

    SysDictForm entity2Form(SysDict entity);

    @InheritInverseConfiguration(name="entity2Form")
    SysDict form2Entity(SysDictForm entity);
}
