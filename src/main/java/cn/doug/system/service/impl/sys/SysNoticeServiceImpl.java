package cn.doug.system.service.impl.sys;

import cn.doug.system.model.entity.SysNoticeEntity;
import cn.doug.system.mapper.SysNoticeMapper;
import cn.doug.system.service.SysNoticeService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.doug.common.util.DateUtils;
import cn.doug.system.model.form.SysNoticeForm;
import cn.doug.system.model.query.SysNoticePageQuery;
import cn.doug.system.model.bo.SysNoticeBO;
import cn.doug.system.model.vo.SysNoticePageVO;
import cn.doug.system.converter.SysNoticeConverter;
import cn.doug.common.base.BaseIdForm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import cn.hutool.core.lang.Assert;

/**
 * 通知公告表服务实现类
 *
 * @author pengqihai
 * @since 2024-08-14
 */
@Service
@RequiredArgsConstructor
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeEntity> implements SysNoticeService {

    private final SysNoticeConverter noticeEntityConverter;

    /**
    * 获取通知公告表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage< SysNoticePageVO >} 通知公告表分页列表
    */
    @Override
    public IPage<SysNoticePageVO> listPagedNotices(SysNoticePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<SysNoticeBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<SysNoticeBO> boPage = this.baseMapper.listPagedNotices(page, queryParams);
    
        // 实体转换
        return noticeEntityConverter.bo2PageVo(boPage);
    }
    
    /**
     * 获取通知公告表表单数据
     *
     * @param id 通知公告表ID
     * @return
     */
    @Override
    public SysNoticeForm getNoticeFormData(String id) {
        SysNoticeEntity entity = this.getById(id);
        return noticeEntityConverter.entity2Form(entity);
    }
    
    /**
     * 新增通知公告表
     *
     * @param formData 通知公告表表单对象
     * @return
     */
    @Override
    public boolean saveNotice(SysNoticeForm formData) {
        // 实体转换 form->entity
        SysNoticeEntity entity = noticeEntityConverter.form2Entity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新通知公告表
     *
     * @param id   通知公告表ID
     * @param formData 通知公告表表单对象
     * @return
     */
    @Override
    public boolean updateNotice(SysNoticeForm formData) {
        SysNoticeEntity entity = noticeEntityConverter.form2Entity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除通知公告表
     *
     * @param form 通知公告表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteNotices(BaseIdForm form) {
        List<String> ids = form.getIds();
        Assert.isTrue(CollUtil.isNotEmpty(ids), "删除的通知公告表数据为空");
        // 逻辑删除
        return this.removeByIds(ids);
    }
    

}
