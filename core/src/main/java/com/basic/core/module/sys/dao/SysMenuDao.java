package com.basic.core.module.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.basic.core.module.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
    /**
     *
     * @param map
     * @return
     */
    List<SysMenu> queryList(Map<String, Object> map);

    /**
     * 根据用户Id查询锁对应的权限
     * @param userId
     * @return
     */
    List<SysMenu> selectMenusByUserId(@Param("userId") Long userId);
}
