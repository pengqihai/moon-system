package cn.doug.system.controller.system;

import cn.doug.common.annotation.WebLog;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.form.DeptForm;
import cn.doug.system.model.query.DeptQuery;
import cn.doug.system.model.vo.DeptVO;
import cn.doug.system.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
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
    public ResultVO<List<DeptVO>> listDepartments(
            @ParameterObject DeptQuery queryParams
    ) {
        List<DeptVO> list = deptService.listDepartments(queryParams);
        return ResultVO.success(list);
    }

    @WebLog(value = "获取部门下拉选项")
    @Operation(summary = "获取部门下拉选项")
    @GetMapping("/options")
    public ResultVO<List<Option>> listDeptOptions() {
        List<Option> list = deptService.listDeptOptions();
        return ResultVO.success(list);
    }

    @WebLog(value = "获取部门表单数据")
    @Operation(summary = "获取部门表单数据")
    @GetMapping("/{deptId}/form")
    public ResultVO<DeptForm> getDeptForm(
            @Parameter(description ="部门ID") @PathVariable Long deptId
    ) {
        DeptForm deptForm = deptService.getDeptForm(deptId);
        return ResultVO.success(deptForm);
    }

    @WebLog(value = "新增部门")
    @Operation(summary = "新增部门")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dept:add')")
    @PreventDuplicateSubmit
    public ResultVO saveDept(
            @Valid @RequestBody DeptForm formData
    ) {
        Long id = deptService.saveDept(formData);
        return ResultVO.success(id);
    }

    @WebLog(value = "修改部门")
    @Operation(summary = "修改部门")
    @PutMapping(value = "/{deptId}")
    @PreAuthorize("@ss.hasPerm('sys:dept:edit')")
    public ResultVO updateDept(
            @PathVariable Long deptId,
            @Valid @RequestBody DeptForm formData
    ) {
        deptId = deptService.updateDept(deptId, formData);
        return ResultVO.success(deptId);
    }

    @WebLog(value = "删除部门")
    @Operation(summary = "删除部门")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dept:delete')")
    public ResultVO deleteDepartments(
            @Parameter(description ="部门ID，多个以英文逗号(,)分割") @PathVariable("ids") String ids
    ) {
        boolean result = deptService.deleteByIds(ids);
        return ResultVO.judge(result);
    }

}
