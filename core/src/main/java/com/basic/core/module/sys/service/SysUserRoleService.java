package com.basic.core.module.sys.service;

import com.basic.core.module.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 添加和更新用户角色
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
