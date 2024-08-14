package cn.doug.system.service;

import cn.doug.system.model.entity.SysNoticeEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.model.form.SysNoticeForm;
import cn.doug.system.model.query.SysNoticePageQuery;
import cn.doug.system.model.vo.SysNoticePageVO;
import cn.doug.common.base.BaseIdForm;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 通知公告表 服务类
 *
 * @author pengqihai
 * @since 2024-08-14
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {


    /**
     *通知公告表分页列表
     *
     * @return
     */
    IPage<SysNoticePageVO> listPagedNotices(SysNoticePageQuery queryParams);


    /**
     * 获取通知公告表表单数据
     *
     * @param id 通知公告表ID
     * @return
     */
     SysNoticeForm getNoticeFormData(String id);


    /**
     * 新增通知公告表
     *
     * @param formData 通知公告表表单对象
     * @return
     */
    boolean saveNotice(SysNoticeForm formData);

    /**
     * 修改通知公告表
     *
     * @param id   通知公告表ID
     * @param formData 通知公告表表单对象
     * @return
     */
    boolean updateNotice(SysNoticeForm formData);


    /**
     * 删除通知公告表
     *
     * @param ids 通知公告表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNotices(BaseIdForm form);

}
