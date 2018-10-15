package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.basic.core.config.ApplicationProperties;
import com.basic.core.module.sys.dao.SysMenuDao;
import com.basic.core.module.sys.dao.SysUserDao;
import com.basic.core.module.sys.entity.SysMenu;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.SysUserToken;
import com.basic.core.module.sys.service.ShiroService;
import com.basic.core.module.sys.service.SysUserTokenService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wenlf on 2018/10/12
 */
@Service
@AllArgsConstructor
public class ShiroServiceImpl implements ShiroService {
    private SysMenuDao sysMenuDao;
    private SysUserDao sysUserDao;
    private SysUserTokenService sysUserTokenService;
    private ApplicationProperties applicationProperties;

    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        //这个验证方法有点问题，需要详细匹配，暂时不改
        if(applicationProperties.getSecurity().getAdminIds().indexOf(userId) != -1){
            List<SysMenu> menuList = sysMenuDao.queryList(new HashMap<>(100));
            permsList = new ArrayList<>(menuList.size());
            for(SysMenu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    /**
     * 根据token查询对象
     *
     * @param token
     * @return
     */
    @Override
    public SysUserToken queryByToken(String token) {
        List<SysUserToken> tokens = sysUserTokenService.selectList(new EntityWrapper<SysUserToken>().eq("token", token));
        return tokens.size() > 0 ? tokens.get(0) : null;
    }

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    @Override
    public SysUser queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
