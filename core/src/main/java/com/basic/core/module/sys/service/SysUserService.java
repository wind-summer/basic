package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysUserAdd;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 创建用户
     * @param user
     */
    void save(SysUserAdd user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUser queryByUserName(String username);

    /**
     * 分页查询
     * @param page
     * @param usernameOrName 条件
     * @return
     */
    Page<SysUser> pages(Page<SysUser> page, String usernameOrName);

    /**
     * 删除用户
     * @param ids
     */
    void delete(String ids);
}
