package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.SysUser;

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
    Page<SysUser> pages(Page<SysRole> page, String roleName);
}
