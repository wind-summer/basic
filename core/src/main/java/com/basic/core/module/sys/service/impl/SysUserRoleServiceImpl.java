package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.module.sys.dao.SysUserRoleDao;
import com.basic.core.module.sys.entity.SysUserRole;
import com.basic.core.module.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    private SysUserRoleDao sysUserRoleDao;
    /**
     * 添加和更新用户角色
     *
     * @param userId
     * @param roleIdList
     */
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }
        //先删除用户与角色关系
        baseMapper.delete(new EntityWrapper<SysUserRole>().eq("user_id", userId));
        //保存用户与角色关系
        roleIdList.forEach(roleId -> baseMapper.insert(new SysUserRole().setUserId(userId).setRoleId(roleId)));
    }
}
