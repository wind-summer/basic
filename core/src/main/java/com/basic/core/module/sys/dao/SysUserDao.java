package com.basic.core.module.sys.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.basic.core.module.sys.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 分页
     * @param page
     * @param usernameOrName
     * @return
     */
    List<SysUser> selectSysUserPages(Pagination page, @Param("usernameOrName") String usernameOrName);
}
