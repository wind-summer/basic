package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.dao.SysRoleDao;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    private SysRoleDao sysRoleDao;
    /**
     * 分页查询
     *
     * @param page
     * @param roleName 角色名称
     * @return
     */
    @Override
    public Page<SysUser> pages(Page<SysRole> page, String roleName) {
        EntityWrapper ew = new EntityWrapper<>();
        if(!StringUtils.isEmpty(roleName)){
            ew.eq("role_name", roleName);
        }
        return this.selectPage(page, ew);
    }
}
