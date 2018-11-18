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
     * 删除菜单 批量删除
     * @param ids
     */
    void deleteMenus(String ids);
}
