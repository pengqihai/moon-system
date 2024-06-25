package cn.doug.system.controller.system;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.common.result.Result;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.query.RegionCodeQuery;
import cn.doug.system.model.vo.digit.RegionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.doug.system.model.form.digit.RegionForm;
import cn.doug.system.model.query.RegionPageQuery;
import cn.doug.system.service.RegionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 地区表 前端控制器
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Tag(name = "08.地区表接口")
@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

        @WebLog(value = "地区下拉列表", logIgnore = true,maxLogChar = 200)
        @Operation(summary = "地区下拉列表")
        @GetMapping("/treeList")
        public Result<List<RegionVO>> listPagedRegions(RegionPageQuery queryParams ) {
            List<RegionVO> result = regionService.listPagedRegions(queryParams);
            return Result.success(result);
        }

    /**
     * 获取地区下拉选项
     * @return options
     */
    @WebLog(value = "获取地区下拉选项",logIgnore = true,maxLogChar = 200)
    @Operation(summary = "获取地区下拉选项")
    @GetMapping("/options")
    public Result<List<Option>> listRegionOptions() {
        List<Option> list = regionService.listRegionOptions();
        return Result.success(list);
    }

    /**
     * 根据父级区划获取列表
     * @return options
     */
    @WebLog(value = "根据父级区划获取列表",logIgnore = true,maxLogChar = 200)
    @Operation(summary = "根据父级区划获取列表")
    @PostMapping("/listRegionByParentCode")
    public Result<List<RegionVO>> listRegionByParentCode(@RequestBody @Validated RegionCodeQuery query) {
        return regionService.listRegionByParentCode(query);
    }


        @WebLog(value = "新增地区")
        @Operation(summary = "新增地区")
        @PostMapping("/add")
        public Result saveRegion(@RequestBody @Valid RegionForm formData ) {
            boolean result = regionService.saveRegion(formData);
            return Result.judge(result);
        }

        @WebLog(value = "获取地区详情")
        @Operation(summary = "获取地区详情")
        @GetMapping("/getInfo/{id}")
        public Result<RegionForm> getRegionForm(
            @Parameter(description = "ID") @PathVariable String id
        ) {
            RegionForm formData = regionService.getRegionFormData(id);
            return Result.success(formData);
        }

        @WebLog(value = "修改地区表")
        @Operation(summary = "修改地区表")
        @PutMapping(value = "edit")
        public Result updateRegion(@RequestBody @Validated RegionForm formData) {
            boolean result = regionService.updateRegion(formData);
            return Result.judge(result);
        }

        @WebLog(value = "删除地区表")
        @Operation(summary = "删除地区表")
        @DeleteMapping("/del/{ids}")
        public Result deleteRegions(
            @Parameter(description = "地区表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = regionService.deleteRegions(ids);
            return Result.judge(result);
        }
}
