package cn.doug.system.service.impl;

import cn.doug.system.model.entity.VeteranCadreEntity;
import cn.doug.system.mapper.VeteranCadreMapper;
import cn.doug.system.model.vo.digit.VeteranCadreExportVO;
import cn.doug.system.service.VeteranCadreService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.doug.common.util.DateUtils;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import cn.doug.system.model.bo.VeteranCadreBO;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.system.converter.VeteranCadreConverter;
import cn.doug.common.base.BaseIdForm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 老干部工作人员与离退休党员	服务实现类
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Service
@RequiredArgsConstructor
public class VeteranCadreServiceImpl extends ServiceImpl<VeteranCadreMapper, VeteranCadreEntity> implements VeteranCadreService {

    private final VeteranCadreConverter veteranCadreEntityConverter;

    /**
    * 获取老干部工作人员与离退休党员	分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<VeteranCadrePageVO>} 老干部工作人员与离退休党员	分页列表
    */
    @Override
    public IPage<VeteranCadrePageVO> listPagedVeteranCadres(VeteranCadrePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<VeteranCadreBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<VeteranCadreBO> boPage = this.baseMapper.listPagedVeteranCadres(page, queryParams);
    
        // 实体转换
        return veteranCadreEntityConverter.bo2PageVo(boPage);
    }
    
    /**
     * 获取老干部工作人员与离退休党员	表单数据
     *
     * @param id 老干部工作人员与离退休党员	ID
     * @return
     */
    @Override
    public VeteranCadreForm getVeteranCadreFormData(String id) {
        Assert.isTrue(StrUtil.isNotEmpty(id), "id为空");
        VeteranCadreEntity entity = this.getById(id);
        return veteranCadreEntityConverter.entity2Form(entity);
    }
    
    /**
     * 新增老干部工作人员与离退休党员
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @Override
    public boolean saveVeteranCadre(VeteranCadreForm formData) {
        // 实体转换 form->entity
        VeteranCadreEntity entity = veteranCadreEntityConverter.form2Entity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新老干部工作人员与离退休党员
     *
     * @param id   老干部工作人员与离退休党员	ID
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @Override
    public boolean updateVeteranCadre(VeteranCadreForm formData) {
        VeteranCadreEntity entity = veteranCadreEntityConverter.form2Entity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除老干部工作人员与离退休党员
     *
     * @param form 老干部工作人员与离退休党员	ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteVeteranCadres(BaseIdForm form) {
        List<String> ids = form.getIds();
        Assert.isTrue(CollUtil.isNotEmpty(ids), "ids为空");
        // 逻辑删除
        return this.removeByIds(ids);
    }

    /**
     * 导出老干部工作人员与离退休党员
     * @throws IOException
     */
    @Override
    public List<VeteranCadreExportVO> listExportVeteranCadres(VeteranCadrePageQuery queryParams) {
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<VeteranCadreEntity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<VeteranCadreEntity> wrapper = new LambdaQueryWrapper<>();
        String keywords = queryParams.getKeywords();
        if (StrUtil.isNotEmpty(keywords)) {
            wrapper.like(VeteranCadreEntity::getFullName,keywords);
        }
        List<VeteranCadreEntity> records = this.page(page, wrapper).getRecords();
        List<VeteranCadreExportVO> veteranCadreExportVOS = veteranCadreEntityConverter.entity2ExportVOList(records);
        return veteranCadreExportVOS;
    }
}
