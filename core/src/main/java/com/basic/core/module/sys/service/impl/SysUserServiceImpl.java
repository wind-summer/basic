package com.basic.core.module.sys.service.impl;

import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.dao.SysUserDao;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.service.SysUserRoleService;
import com.basic.core.module.sys.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    private SysUserDao sysUserDao;
    private SysUserRoleService sysUserRoleService;
    /**
     * 创建用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = )
    public void save(SysUserAdd user) {
        SysUser newUser = new SysUser();
        BeanUtils.copyProperties(user,newUser);
        newUser.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        newUser.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        newUser.setSalt(salt);
        sysUserDao.insert(newUser);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(newUser.getUserId(), newUser.getRoleIdList());
    }
}
