package com.basic.core.module.sys.service;

import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.SysUserToken;

import java.util.Set;

/**
 * shiro 相关接口
 * Created by wenlf on 2018/10/12
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据token查询对象
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(Long userId);
}
