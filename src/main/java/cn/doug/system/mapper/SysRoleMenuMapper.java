package cn.doug.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.doug.system.model.bo.RolePermsBO;
import cn.doug.system.model.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色菜单访问层
 *
 * @author pengqihai
 * @since 2022/6/4
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> listMenuIdsByRoleId(Long roleId);

    /**
     * 获取权限和拥有权限的角色列表
     */
    List<RolePermsBO> getRolePermsList(String roleCode);
}
