package cn.doug.system.controller.system;

import cn.doug.common.annotation.WebLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.vo.PageResultVO;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.form.DictForm;
import cn.doug.system.model.form.DictTypeForm;
import cn.doug.system.model.query.DictPageQuery;
import cn.doug.system.model.query.DictTypePageQuery;
import cn.doug.system.model.vo.DictPageVO;
import cn.doug.system.model.vo.DictTypePageVO;
import cn.doug.system.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import cn.doug.system.service.SysDictService;
import cn.doug.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService dictService;

    private final SysDictTypeService dictTypeService;

    @WebLog(value = "字典分页列表")
    @Operation(summary = "字典分页列表")
    @GetMapping("/page")
    public PageResultVO<DictPageVO> getDictPage(
            @ParameterObject DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResultVO.success(result);
    }

    @WebLog(value = "字典数据表单数据")
    @Operation(summary = "字典数据表单数据")
    @GetMapping("/{id}/form")
    public ResultVO<DictForm> getDictForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getDictForm(id);
        return ResultVO.success(formData);
    }

    @WebLog(value = "新增字典")
    @Operation(summary = "新增字典")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dict:add')")
    @PreventDuplicateSubmit
    public ResultVO saveDict(
            @RequestBody DictForm DictForm
    ) {
        boolean result = dictService.saveDict(DictForm);
        return ResultVO.judge(result);
    }

    @WebLog(value = "修改字典")
    @Operation(summary = "修改字典")
    @PutMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:dict:edit')")
    public ResultVO updateDict(
            @PathVariable Long id,
            @RequestBody DictForm DictForm
    ) {
        boolean status = dictService.updateDict(id, DictForm);
        return ResultVO.judge(status);
    }

    @WebLog(value = "删除字典")
    @Operation(summary = "删除字典")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dict:delete')")
    public ResultVO deleteDict(
            @Parameter(description ="字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = dictService.deleteDict(ids);
        return ResultVO.judge(result);
    }


    @WebLog(value = "字典下拉列表")
    @Operation(summary = "字典下拉列表")
    @GetMapping("/{typeCode}/options")
    public ResultVO<List<Option>> listDictOptions(
            @Parameter(description ="字典类型编码") @PathVariable String typeCode
    ) {
        List<Option> list = dictService.listDictOptions(typeCode);
        return ResultVO.success(list);
    }


    /*----------------------------------------------------*/
    @WebLog(value = "字典类型分页列表")
    @Operation(summary = "字典类型分页列表")
    @GetMapping("/types/page")
    public PageResultVO<DictTypePageVO> getDictTypePage(
            @ParameterObject DictTypePageQuery queryParams
    ) {
        Page<DictTypePageVO> result = dictTypeService.getDictTypePage(queryParams);
        return PageResultVO.success(result);
    }

    @WebLog(value = "字典类型表单数据")
    @Operation(summary = "字典类型表单数据")
    @GetMapping("/types/{id}/form")
    public ResultVO<DictTypeForm> getDictTypeForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        DictTypeForm dictTypeForm = dictTypeService.getDictTypeForm(id);
        return ResultVO.success(dictTypeForm);
    }

    @WebLog(value = "新增字典类型")
    @Operation(summary = "新增字典类型")
    @PostMapping("/types")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:add')")
    @PreventDuplicateSubmit
    public ResultVO saveDictType(@RequestBody DictTypeForm dictTypeForm) {
        boolean result = dictTypeService.saveDictType(dictTypeForm);
        return ResultVO.judge(result);
    }

    @WebLog(value = "修改字典类型")
    @Operation(summary = "修改字典类型")
    @PutMapping("/types/{id}")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:edit')")
    public ResultVO updateDictType(@PathVariable Long id, @RequestBody DictTypeForm dictTypeForm) {
        boolean status = dictTypeService.updateDictType(id, dictTypeForm);
        return ResultVO.judge(status);
    }

    @WebLog(value = "删除字典类型")
    @Operation(summary = "删除字典类型")
    @DeleteMapping("/types/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dict_type:delete')")
    public ResultVO deleteDictTypes(
            @Parameter(description ="字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = dictTypeService.deleteDictTypes(ids);


        return ResultVO.judge(result);
    }

}
