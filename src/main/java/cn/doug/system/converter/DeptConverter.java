package cn.doug.system.converter;

import cn.doug.system.model.entity.SysDept;
import cn.doug.system.model.form.sys.DeptForm;
import cn.doug.system.model.vo.sys.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author pengqihai
 * @since 2022/7/29
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm entity2Form(SysDept entity);
    DeptVO entity2Vo(SysDept entity);

    SysDept form2Entity(DeptForm deptForm);

}