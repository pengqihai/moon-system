package cn.doug.system.mapper;

import cn.doug.system.model.entity.VeteranCadreEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.bo.VeteranCadreBO;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 老干部工作人员与离退休党员	 Mapper 接口
 *
 * @author pengqihai
 * @since 2024-06-25
 */

@Mapper
public interface VeteranCadreMapper extends BaseMapper<VeteranCadreEntity> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<VeteranCadreBO> listPagedVeteranCadres(Page<VeteranCadreBO> page, VeteranCadrePageQuery queryParams);

}
