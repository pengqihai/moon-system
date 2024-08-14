package cn.doug.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.doug.system.model.entity.SysNoticeEntity;
import cn.doug.system.model.vo.SysNoticePageVO;
import cn.doug.system.model.form.SysNoticeForm;
import cn.doug.system.model.bo.SysNoticeBO;

/**
 * 通知公告表转换器
 *
 * @author pengqihai
 * @since 2024-08-14
 */
@Mapper(componentModel = "spring")
public interface SysNoticeConverter {

    SysNoticePageVO bo2PageVo(SysNoticeBO bo);

    Page<SysNoticePageVO> bo2PageVo(Page<SysNoticeBO> bo);

    SysNoticeForm entity2Form(SysNoticeEntity entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysNoticeEntity form2Entity(SysNoticeForm entity);
}