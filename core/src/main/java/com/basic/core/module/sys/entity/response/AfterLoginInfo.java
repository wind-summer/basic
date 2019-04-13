package com.basic.core.module.sys.entity.response;

import com.basic.core.module.sys.entity.SysMenu;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.entity.SysUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 登录之后返回给前台的
 * 用户相关信息(用户，权限，菜单等）
 * </p>
 *
 * @author wenlongfei
 * @since 2019/4/11
 */
@Data
@Accessors(chain = true)
public class AfterLoginInfo {
    private SysUser user;
    private List<SysMenu> menus;
    private List<SysRole> roles;
}
