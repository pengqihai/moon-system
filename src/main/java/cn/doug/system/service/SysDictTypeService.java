package cn.doug.system.service;

import cn.doug.system.model.vo.SysDictTypePageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysDictType;
import cn.doug.system.model.form.SysDictTypeForm;
import cn.doug.system.model.query.SysDictTypePageQuery;

import java.util.List;

/**
 * 数据字典类型业务接口
 *
 * @author pengqihai
 * @since 2022/10/12
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 字典类型分页列表
     *
     * @param queryParams 分页查询对象
     * @return
     */
    Page<SysDictTypePageVO> getDictTypePage(SysDictTypePageQuery queryParams);


    /**
     * 获取字典类型表单详情
     *
     * @param id 字典类型ID
     * @return
     */
    SysDictTypeForm getDictTypeForm(Long id);


    /**
     * 新增字典类型
     *
     * @param dictTypeForm 字典类型表单
     * @return
     */
    boolean saveDictType(SysDictTypeForm dictTypeForm);


    /**
     * 修改字典类型
     *
     * @param id
     * @param dictTypeForm 字典类型表单
     * @return
     */
    boolean updateDictType(Long id, SysDictTypeForm dictTypeForm);

    /**
     * 删除字典类型
     *
     * @param idsStr 字典类型ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteDictTypes(String idsStr);


    /**
     * 获取字典类型的数据项
     *
     * @param typeCode
     * @return
     */
    List<Option> listDictItemsByTypeCode(String typeCode);
}
