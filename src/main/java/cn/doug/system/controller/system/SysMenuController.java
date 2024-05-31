package cn.doug.system.controller.system;

import cn.doug.common.annotation.WebLog;
import cn.doug.system.common.model.Option;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.form.MenuForm;
import cn.doug.system.model.query.MenuQuery;
import cn.doug.system.model.vo.MenuVO;
import cn.doug.system.model.vo.RouteVO;
import cn.doug.system.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import cn.doug.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SysMenuController {

    private final SysMenuService menuService;

    @WebLog(value = "菜单列表")
    @Operation(summary = "菜单列表")
    @GetMapping
    public ResultVO<List<MenuVO>> listMenus( @ParameterObject MenuQuery queryParams) {
        List<MenuVO> menuList = menuService.listMenus(queryParams);
        return ResultVO.success(menuList);
    }

    @WebLog(value = "菜单下拉列表")
    @Operation(summary = "菜单下拉列表")
    @GetMapping("/options")
    public ResultVO listMenuOptions() {
        List<Option> menus = menuService.listMenuOptions();
        return ResultVO.success(menus);
    }

    @WebLog(value = "路由列表")
    @Operation(summary = "路由列表")
    @GetMapping("/routes")
    public ResultVO<List<RouteVO>> listRoutes() {
        List<RouteVO> routeList = menuService.listRoutes();
        return ResultVO.success(routeList);
    }

    @WebLog(value = "菜单表单数据")
    @Operation(summary = "菜单表单数据")
    @GetMapping("/{id}/form")
    public ResultVO<MenuForm> getMenuForm(
            @Parameter(description =  "菜单ID") @PathVariable Long id
    ) {
        MenuForm menu = menuService.getMenuForm(id);
        return ResultVO.success(menu);
    }

    @WebLog(value = "新增菜单")
    @Operation(summary = "新增菜单")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:menu:add')")
    @PreventDuplicateSubmit
    public ResultVO addMenu(@RequestBody MenuForm menuForm) {
        boolean result = menuService.saveMenu(menuForm);
        return ResultVO.judge(result);
    }

    @WebLog(value = "修改菜单")
    @Operation(summary = "修改菜单")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:edit')")
    public ResultVO updateMenu(
            @RequestBody MenuForm menuForm
    ) {
        boolean result = menuService.saveMenu(menuForm);
        return ResultVO.judge(result);
    }

    @WebLog(value = "删除菜单")
    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:delete')")
    public ResultVO deleteMenu(
            @Parameter(description ="菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
    ) {
        boolean result = menuService.deleteMenu(id);
        return ResultVO.judge(result);
    }

    @WebLog(value = "修改菜单显示状态")
    @Operation(summary = "修改菜单显示状态")
    @PatchMapping("/{menuId}")
    public ResultVO updateMenuVisible(
            @Parameter(description =  "菜单ID") @PathVariable Long menuId,
            @Parameter(description =  "显示状态(1:显示;0:隐藏)") Integer visible

    ) {
        boolean result =menuService.updateMenuVisible(menuId, visible);
        return ResultVO.judge(result);
    }


}

