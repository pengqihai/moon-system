package cn.doug.system.service;

import cn.doug.system.model.form.SysMenuForm;
import cn.doug.system.model.query.SysMenuQuery;
import cn.doug.system.model.vo.SysMenuVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysMenu;
import cn.doug.system.model.vo.SysRouteVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务接口
 * 
 * @author pengqihai
 * @since 2020/11/06
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单表格列表
     *
     * @return
     */
    List<SysMenuVO> listMenus(SysMenuQuery queryParams);


    /**
     * 获取菜单下拉列表
     *
     * @return
     */
    List<Option> listMenuOptions();

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    boolean saveMenu(SysMenuForm menu);

    /**
     * 获取路由列表
     *
     * @return
     */
    List<SysRouteVO> listRoutes();

    /**
     * 修改菜单显示状态
     * 
     * @param menuId 菜单ID
     * @param visible 是否显示(1->显示；2->隐藏)
     * @return
     */
    boolean updateMenuVisible(Long menuId, Integer visible);

    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(Set<String> roles);

    /**
     * 获取菜单表单数据
     *
     * @param id 菜单ID
     * @return
     */
    SysMenuForm getMenuForm(Long id);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    boolean deleteMenu(Long id);
}
