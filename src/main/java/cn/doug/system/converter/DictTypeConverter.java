package cn.doug.system.converter;

import cn.doug.system.model.form.sys.SysDictTypeForm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.entity.SysDictType;
import cn.doug.system.model.vo.sys.DictTypePageVO;
import org.mapstruct.Mapper;

/**
 * 字典类型对象转换器
 *
 * @author pengqihai
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    SysDictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(SysDictTypeForm entity);
}
