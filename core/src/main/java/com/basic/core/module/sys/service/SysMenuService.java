package com.basic.core.module.sys.service;

import com.basic.core.module.sys.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.request.SysMenuAdd;
import com.basic.core.module.sys.entity.request.SysMenuUpdate;
import com.basic.core.module.sys.entity.response.ParentTree;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询所有的菜单
     */
    List<SysMenu> findAllMenus();

    /**
     * 获取上级菜单模型
     */
    List<ParentTree> parentTrees();

    /**
     * 添加菜单
     * @param sysMenu
     */
    void addMenu(SysMenuAdd sysMenu);

    /**
     * 修改菜单
     * @param sysMenu
     */
    void updateMenu(SysMenuUpdate sysMenu);

    /**
     * 删除菜单 单个删除，有子节点不允许删除，必须子节点删除完全之后才能删除
     * @param id
     */
    void deleteMenus(Long id);

    /**
     *  根据用户id查询对应的菜单权限
     * @param userId
     * @return
     */
    List<SysMenu> getMenusByUserId(Long userId);
}
