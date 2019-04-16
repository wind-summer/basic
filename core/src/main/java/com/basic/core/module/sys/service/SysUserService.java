package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.entity.request.SysUserSwitch;
import com.basic.core.module.sys.entity.request.SysUserUpdate;
import com.basic.core.module.sys.entity.response.AfterLoginInfo;

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
     * 根据id查询用户信息，包括角色
     * @param id
     * @return
     */
    SysUser userInfo(Long id);

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

    /**
     * 修改用户
     * @param user
     */
    void update(SysUserUpdate user);

    /**
     * 修改个人密码
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(String oldPassword, String newPassword);

    /**
     * 修改用户状态
     * @param userSwitch
     */
    void userSwitch(SysUserSwitch userSwitch);

    /**
     * 获取当前登录用户的登录信息，权限等
     * @return
     */
    AfterLoginInfo getLongInfo();
}
