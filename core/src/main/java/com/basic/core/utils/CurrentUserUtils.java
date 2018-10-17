package com.basic.core.utils;

import com.basic.core.module.sys.entity.SysUser;

/**
 * 获取当前登陆用户
 * Created by wenlf on 2018/10/17
 */
public class CurrentUserUtils {
    private static final ThreadLocal<SysUser> loginThreadLocal = new InheritableThreadLocal<>();

    public static final String LOGIN_ID = "loginId";

    public static final String HEADER_TOKEN_KEY = "token";

    public static SysUser getLogin() {

        return loginThreadLocal.get();
    }

    public static void setLogin(SysUser customerLogin) {
        loginThreadLocal.set(customerLogin);
    }

    public static void removeLogin(){
        loginThreadLocal.remove();
    }
}
