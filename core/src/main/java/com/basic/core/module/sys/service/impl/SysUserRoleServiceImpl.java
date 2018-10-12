package com.basic.core.module.sys.service.impl;

import com.basic.core.module.sys.entity.SysUserRole;
import com.basic.core.module.sys.dao.SysUserRoleDao;
import com.basic.core.module.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        sysUserRoleDao.deleteByUserId(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        sysUserRoleDao.save(map);
    }
}
