package cn.doug.system.service.impl;

import cn.doug.common.result.Result;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysRegion;
import cn.doug.system.mapper.RegionMapper;
import cn.doug.system.model.query.RegionCodeQuery;
import cn.doug.system.model.vo.RegionVO;
import cn.doug.system.service.RegionService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.doug.system.model.form.RegionForm;
import cn.doug.system.model.query.RegionPageQuery;
import cn.doug.system.model.vo.RegionPageVO;
import cn.doug.system.converter.RegionConverter;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 地区表服务实现类
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends ServiceImpl<RegionMapper, SysRegion> implements RegionService {

    private final RegionConverter regionConverter;

    /**
    * 获取地区表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<RegionPageVO>} 地区表分页列表
    */
    @Override
    public List<RegionVO> listPagedRegions(RegionPageQuery queryParams) {

        List<SysRegion> regions = this.list(new LambdaQueryWrapper<SysRegion>()
                .like(StrUtil.isNotBlank(queryParams.getKeywords()), SysRegion::getRegionName, queryParams.getKeywords())

        );
        // 获取地区编号
        Set<String> regionCodes = regions.stream()
                .map(SysRegion::getRegionCode)
                .collect(Collectors.toSet());

        // 获取所有父级编号
        Set<String> parentCodes = regions.stream()
                .map(SysRegion::getRegionParentCode)
                .collect(Collectors.toSet());

        // 获取根节点编号（递归的起点），即父节点编号中不包含在部门编号中的节点，注意这里不能拿顶级菜单 O 作为根节点，因为菜单筛选的时候 O 会被过滤掉
        List<String> rootIds = parentCodes.stream()
                .filter(id -> !regionCodes.contains(id))
                .toList();

        // 使用递归函数来构建菜单树
        return rootIds.stream()
                .flatMap(rootId -> buildMenuTree(rootId, regions).stream())
                .collect(Collectors.toList());
    }

    /**
     * 递归生成区划列表
     *
     * @param parentCode 父级编号
     * @param regionList 区划列表
     * @return 区划列表
     */
    private List<RegionVO> buildMenuTree(String parentCode, List<SysRegion> regionList) {
        return CollectionUtil.emptyIfNull(regionList)
                .stream()
                .filter(menu -> menu.getRegionParentCode().equals(parentCode))
                .map(entity -> {
                    RegionVO menuVO = regionConverter.entity2Vo(entity);
                    List<RegionVO> children = buildMenuTree(entity.getRegionCode(), regionList);
                    menuVO.setChildren(children);
                    return menuVO;
                }).toList();
    }

    @Override
    public List<Option> listRegionOptions() {
        List<SysRegion> regionList = this.list(new LambdaQueryWrapper<SysRegion>()
                .select(SysRegion::getRegionCode, SysRegion::getRegionParentCode, SysRegion::getRegionName)
        );
        if (CollectionUtil.isEmpty(regionList)) {
            return Collections.EMPTY_LIST;
        }

        Set<String> regionIds = regionList.stream()
                .map(SysRegion::getRegionCode)
                .collect(Collectors.toSet());

        Set<String> parentIds = regionList.stream()
                .map(SysRegion::getRegionParentCode)
                .collect(Collectors.toSet());

        List<String> rootIds = CollectionUtil.subtractToList(parentIds, regionIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurRegionTreeOptions(rootId, regionList).stream())
                .toList();
    }

    /**
     * 根据父级区划获取列表
     * @return options
     */
    @Override
    public Result<List<RegionVO>> listRegionByParentCode(RegionCodeQuery query) {
        LambdaQueryWrapper<SysRegion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotEmpty(query.getAreaCode()), SysRegion::getRegionParentCode,query.getAreaCode());
        List<SysRegion> list = this.list(wrapper);
        List<RegionVO> regionVOS = regionConverter.entitys2VOS(list);
        return Result.success(regionVOS);
    }

    /**
     * 获取地区表表单数据
     *
     * @param id 地区表ID
     * @return
     */
    @Override
    public RegionForm getRegionFormData(String id) {
        SysRegion entity = this.getById(id);
        return regionConverter.entity2Form(entity);
    }
    
    /**
     * 新增地区表
     *
     * @param formData 地区表表单对象
     * @return
     */
    @Override
    public boolean saveRegion(RegionForm formData) {
        // 实体转换 form->entity
        SysRegion entity = regionConverter.form2Entity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新地区表
     *
     * @param formData 地区表表单对象
     * @return
     */
    @Override
    public boolean updateRegion(RegionForm formData) {
        SysRegion entity = regionConverter.form2Entity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除地区表
     *
     * @param ids 地区表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteRegions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的地区表数据为空");
        // 逻辑删除
        List<String> idList = Arrays.stream(ids.split(","))
                //.map(String::)
                .toList();
        return this.removeByIds(idList);
    }

    /**
     * 递归生成部门表格层级列表
     *
     * @param parentId 父ID
     * @param regionList 部门列表
     * @return 部门表格层级列表
     */
    public static List<Option> recurRegionTreeOptions(String parentId, List<SysRegion> regionList) {
        List<Option> list = CollectionUtil.emptyIfNull(regionList).stream()
                .filter(region -> region.getRegionParentCode().equals(parentId))
                .map(region -> {
                    Option option = new Option(region.getRegionCode(), region.getRegionName());
                    List<Option> children = recurRegionTreeOptions(region.getRegionCode(), regionList);
                    if (CollectionUtil.isNotEmpty(children)) {
                        option.setChildren(children);
                    }
                    return option;
                })
                .collect(Collectors.toList());
        return list;
    }

}
