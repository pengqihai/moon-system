package cn.doug.system.mapper;

import cn.doug.system.model.entity.SysNoticeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.bo.SysNoticeBO;
import cn.doug.system.model.query.SysNoticePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知公告表 Mapper 接口
 *
 * @author pengqihai
 * @since 2024-08-14
 */

@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNoticeEntity> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<SysNoticeBO> listPagedNotices(Page<SysNoticeBO> page, SysNoticePageQuery queryParams);

}
