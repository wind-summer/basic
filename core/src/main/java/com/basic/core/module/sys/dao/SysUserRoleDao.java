package com.basic.core.module.sys.dao;

import com.basic.core.module.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 清除用户所有角色
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 保存用户权限
     * @param map
     */
    void save(Map<String, Object> map);
}
