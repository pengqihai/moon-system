package cn.doug.system.controller.system;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.Result;
import cn.doug.system.model.form.SysMenuForm;
import cn.doug.system.model.query.SysMenuQuery;
import cn.doug.system.model.vo.SysMenuVO;
import cn.doug.system.model.vo.SysRouteVO;
import cn.doug.common.plugin.annotation.PreventDuplicateSubmit;
import cn.doug.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 *
 * @author pengqihai
 * @since 2020/11/06
 */
@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Log4j2
public class SysMenuController {

    private final SysMenuService menuService;

    @WebLog(value = "菜单列表")
    @Operation(summary = "菜单列表")
    @GetMapping
    public Result<List<SysMenuVO>> listMenus(@ParameterObject SysMenuQuery queryParams) {
        List<SysMenuVO> menuList = menuService.listMenus(queryParams);
        return Result.success(menuList);
    }

    @WebLog(value = "菜单下拉列表")
    @Operation(summary = "菜单下拉列表")
    @GetMapping("/options")
    public Result listMenuOptions() {
        List<Option> menus = menuService.listMenuOptions();
        return Result.success(menus);
    }

    @WebLog(value = "路由列表")
    @Operation(summary = "路由列表")
    @GetMapping("/routes")
    public Result<List<SysRouteVO>> listRoutes() {
        List<SysRouteVO> routeList = menuService.listRoutes();
        return Result.success(routeList);
    }

    @WebLog(value = "菜单表单数据")
    @Operation(summary = "菜单表单数据")
    @GetMapping("/{id}/form")
    public Result<SysMenuForm> getMenuForm(
            @Parameter(description =  "菜单ID") @PathVariable Long id
    ) {
        SysMenuForm menu = menuService.getMenuForm(id);
        return Result.success(menu);
    }

    @WebLog(value = "新增菜单")
    @Operation(summary = "新增菜单")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:menu:add')")
    @PreventDuplicateSubmit
    public Result addMenu(@RequestBody SysMenuForm menuForm) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @WebLog(value = "修改菜单")
    @Operation(summary = "修改菜单")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:edit')")
    public Result updateMenu(
            @RequestBody SysMenuForm menuForm
    ) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @WebLog(value = "删除菜单")
    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:delete')")
    public Result deleteMenu(
            @Parameter(description ="菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
    ) {
        boolean result = menuService.deleteMenu(id);
        return Result.judge(result);
    }

    @WebLog(value = "修改菜单显示状态")
    @Operation(summary = "修改菜单显示状态")
    @PatchMapping("/{menuId}")
    public Result updateMenuVisible(
            @Parameter(description =  "菜单ID") @PathVariable Long menuId,
            @Parameter(description =  "显示状态(1:显示;0:隐藏)") Integer visible

    ) {
        boolean result =menuService.updateMenuVisible(menuId, visible);
        return Result.judge(result);
    }


}

