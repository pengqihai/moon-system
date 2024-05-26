package cn.doug.system.common.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.entity.SysDictType;
import cn.doug.system.model.form.DictTypeForm;
import cn.doug.system.model.vo.DictTypePageVO;
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

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);
}
