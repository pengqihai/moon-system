package cn.doug.system.converter;

import cn.doug.system.model.entity.SysDept;
import cn.doug.system.model.form.SysDeptForm;
import cn.doug.system.model.vo.SysDeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author pengqihai
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface SysDeptConverter {

    SysDeptForm entity2Form(SysDept entity);
    SysDeptVO entity2Vo(SysDept entity);

    SysDept form2Entity(SysDeptForm deptForm);

}