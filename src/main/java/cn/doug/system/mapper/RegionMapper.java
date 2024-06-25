package cn.doug.system.mapper;

import cn.doug.system.model.entity.SysRegion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.bo.RegionBO;
import cn.doug.system.model.query.RegionPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地区表 Mapper 接口
 *
 * @author pengqihai
 * @since 2024-06-05
 */

@Mapper
public interface RegionMapper extends BaseMapper<SysRegion> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<RegionBO> listPagedRegions(Page<RegionBO> page, RegionPageQuery queryParams);

}
