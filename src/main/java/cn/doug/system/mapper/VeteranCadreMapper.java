package cn.doug.system.mapper;

import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.model.vo.VeteranCadreExportVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.bo.VeteranCadreBO;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 老干部工作人员与离退休党员	 Mapper 接口
 *
 * @author pengqihai
 * @since 2024-06-25
 */

@Mapper
public interface VeteranCadreMapper extends BaseMapper<VeteranCadreEntity> {

    /**
     * 获取老干部工作人员与离退休党员分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<VeteranCadreBO> listPagedVeteranCadres(Page<VeteranCadreBO> page, VeteranCadrePageQuery queryParams);


    /**
     * 获取导出老干部工作人员与离退休党员列表
     *
     * @param queryParams
     * @return
     */
    List<VeteranCadreExportVO> listExportUsers(VeteranCadrePageQuery queryParams);
}
