package cn.doug.system.controller.system;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.Result;
import cn.doug.system.model.form.SysDeptForm;
import cn.doug.system.model.query.SysDeptQuery;
import cn.doug.system.model.vo.SysDeptVO;
import cn.doug.common.plugin.annotation.PreventDuplicateSubmit;
import cn.doug.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 *
 * @author pengqihai
 * @since 2020/11/6
 */
@Tag(name = "05.部门接口")
@RestController
@RequestMapping("/api/v1/dept")
@RequiredArgsConstructor
public class SysDeptController {
    private final SysDeptService deptService;
    @WebLog(value = "获取部门列表")
    @Operation(summary = "获取部门列表")
    @GetMapping
    public Result<List<SysDeptVO>> listDepartments(
            @ParameterObject SysDeptQuery queryParams
    ) {
        List<SysDeptVO> list = deptService.listDepartments(queryParams);
        return Result.success(list);
    }

    @WebLog(value = "获取部门下拉选项")
    @Operation(summary = "获取部门下拉选项")
    @GetMapping("/options")
    public Result<List<Option>> listDeptOptions() {
        List<Option> list = deptService.listDeptOptions();
        return Result.success(list);
    }

    @WebLog(value = "获取部门表单数据")
    @Operation(summary = "获取部门表单数据")
    @GetMapping("/{deptId}/form")
    public Result<SysDeptForm> getDeptForm(
            @Parameter(description ="部门ID") @PathVariable Long deptId
    ) {
        SysDeptForm deptForm = deptService.getDeptForm(deptId);
        return Result.success(deptForm);
    }

    @WebLog(value = "新增部门")
    @Operation(summary = "新增部门")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dept:add')")
    @PreventDuplicateSubmit
    public Result saveDept(
            @Valid @RequestBody SysDeptForm formData
    ) {
        Long id = deptService.saveDept(formData);
        return Result.success(id);
    }

    @WebLog(value = "修改部门")
    @Operation(summary = "修改部门")
    @PutMapping(value = "/{deptId}")
    @PreAuthorize("@ss.hasPerm('sys:dept:edit')")
    public Result updateDept(
            @PathVariable Long deptId,
            @Valid @RequestBody SysDeptForm formData
    ) {
        deptId = deptService.updateDept(deptId, formData);
        return Result.success(deptId);
    }

    @WebLog(value = "删除部门")
    @Operation(summary = "删除部门")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dept:delete')")
    public Result deleteDepartments(
            @Parameter(description ="部门ID，多个以英文逗号(,)分割") @PathVariable("ids") String ids
    ) {
        boolean result = deptService.deleteByIds(ids);
        return Result.judge(result);
    }

}
