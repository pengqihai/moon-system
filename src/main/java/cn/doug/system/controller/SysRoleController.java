package cn.doug.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.vo.PageResultVO;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.form.RoleForm;
import cn.doug.system.model.query.RolePageQuery;
import cn.doug.system.model.vo.RolePageVO;
import cn.doug.system.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import cn.doug.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "03.角色接口")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    @Operation(summary = "角色分页列表")
    @GetMapping("/page")
    public PageResultVO<RolePageVO> getRolePage(
            @ParameterObject RolePageQuery queryParams
    ) {
        Page<RolePageVO> result = roleService.getRolePage(queryParams);
        return PageResultVO.success(result);
    }

    @Operation(summary = "角色下拉列表")
    @GetMapping("/options")
    public ResultVO<List<Option>> listRoleOptions() {
        List<Option> list = roleService.listRoleOptions();
        return ResultVO.success(list);
    }

    @Operation(summary = "新增角色")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:role:add')")
    @PreventDuplicateSubmit
    public ResultVO addRole(@Valid @RequestBody RoleForm roleForm) {
        boolean result = roleService.saveRole(roleForm);
        return ResultVO.judge(result);
    }

    @Operation(summary = "角色表单数据")
    @GetMapping("/{roleId}/form")
    public ResultVO<RoleForm> getRoleForm(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        RoleForm roleForm = roleService.getRoleForm(roleId);
        return ResultVO.success(roleForm);
    }

    @Operation(summary = "修改角色")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:role:edit')")
    public ResultVO updateRole(@Valid @RequestBody RoleForm roleForm) {
        boolean result = roleService.saveRole(roleForm);
        return ResultVO.judge(result);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:role:delete')")
    public ResultVO deleteRoles(
            @Parameter(description = "删除角色，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = roleService.deleteRoles(ids);
        return ResultVO.judge(result);
    }

    @Operation(summary = "修改角色状态")
    @PutMapping(value = "/{roleId}/status")
    public ResultVO updateRoleStatus(
            @Parameter(description = "角色ID") @PathVariable Long roleId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = roleService.updateRoleStatus(roleId, status);
        return ResultVO.judge(result);
    }

    @Operation(summary = "获取角色的菜单ID集合")
    @GetMapping("/{roleId}/menuIds")
    public ResultVO<List<Long>> getRoleMenuIds(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        List<Long> menuIds = roleService.getRoleMenuIds(roleId);
        return ResultVO.success(menuIds);
    }

    @Operation(summary = "分配菜单(包括按钮权限)给角色")
    @PutMapping("/{roleId}/menus")
    public ResultVO assignMenusToRole(
            @PathVariable Long roleId,
            @RequestBody List<Long> menuIds
    ) {
        boolean result = roleService.assignMenusToRole(roleId, menuIds);
        return ResultVO.judge(result);
    }
}
