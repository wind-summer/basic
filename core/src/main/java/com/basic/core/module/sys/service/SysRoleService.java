package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysRoleAdd;
import com.basic.core.module.sys.entity.request.SysRoleUpdate;
import com.basic.core.module.sys.entity.response.SysRoleInfo;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询
     * @param page
     * @param roleName 角色名称
     * @return
     */
    Page<SysRole> pages(Page<SysRole> page, String roleName);

    /**
     * 添加角色
     * @param role
     */
    void addRole(SysRoleAdd role);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(SysRoleUpdate role);

    /**
     * 删除角色|可以批量删除
     * @param ids
     */
    void deleteRoles(String ids);

    /**
     * 根据id查询相应的选中菜单
     * @param id
     * @return
     */
    SysRoleInfo detail(Long id);
}
