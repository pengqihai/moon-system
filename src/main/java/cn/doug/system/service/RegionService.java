package cn.doug.system.service;

import cn.doug.common.result.Result;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysRegion;
import cn.doug.system.model.query.digit.RegionCodeQuery;
import cn.doug.system.model.vo.digit.RegionVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.model.form.digit.RegionForm;
import cn.doug.system.model.query.digit.RegionPageQuery;

import java.util.List;

/**
 * 地区表 服务类
 *
 * @author pengqihai
 * @since 2024-06-05
 */
public interface RegionService extends IService<SysRegion> {


    /**
     *地区表分页列表
     *
     * @return
     */
    List<RegionVO> listPagedRegions(RegionPageQuery queryParams);


    /**
     * 获取地区下拉选项
     * @return options
     */
    List<Option> listRegionOptions();

    /**
     * 根据父级区划获取列表
     * @return options
     */
    Result<List<RegionVO>> listRegionByParentCode(RegionCodeQuery query);

    /**
     * 获取地区表表单数据
     *
     * @param id 地区表ID
     * @return
     */
     RegionForm getRegionFormData(String id);


    /**
     * 新增地区表
     *
     * @param formData 地区表表单对象
     * @return
     */
    boolean saveRegion(RegionForm formData);

    /**
     * 修改地区表
     *
     * @param formData 地区表表单对象
     * @return
     */
    boolean updateRegion(RegionForm formData);


    /**
     * 删除地区表
     *
     * @param ids 地区表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteRegions(String ids);


}
