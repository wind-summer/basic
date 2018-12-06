package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.exception.BizException;
import com.basic.core.module.sys.dao.SysRoleMenuDao;
import com.basic.core.module.sys.entity.SysMenu;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.dao.SysRoleDao;
import com.basic.core.module.sys.entity.SysRoleMenu;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysRoleAdd;
import com.basic.core.module.sys.entity.request.SysRoleUpdate;
import com.basic.core.module.sys.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private SysRoleMenuDao sysRoleMenuDao;
    /**
     * 分页查询
     *
     * @param page
     * @param roleName 角色名称
     * @return
     */
    @Override
    public Page<SysRole> pages(Page<SysRole> page, String roleName) {
        EntityWrapper ew = new EntityWrapper<SysRole>();
        if(!StringUtils.isEmpty(roleName)){
            ew.like("role_name", roleName);
        }
        return this.selectPage(page, ew);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(SysRoleAdd role) {
        SysRole newRole = new SysRole();
        BeanUtils.copyProperties(role, newRole);
        newRole.setCreateTime(new Date());
        sysRoleDao.insert(newRole);
        //保存关联关系
        if(!Util.isEmpty(role.getMenuIds())){
            role.getMenuIds().stream().forEach(id -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(id).setRoleId(newRole.getId());
                sysRoleMenuDao.insert(roleMenu);
            });
        }
    }

    /**
     * 修改角色
     *
     * @param role
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRoleUpdate role) {
        SysRole old = this.baseMapper.selectById(role.getId());
        if(old == null){
            throw new BizException("没有相应的角色可以修改");
        }
        BeanUtils.copyProperties(role, old);
        this.baseMapper.updateById(old);

        //添加新的关系
        if(!Util.isEmpty(role.getMenuIds())){
            //删除原来关联关系
            sysRoleMenuDao.delete(new EntityWrapper<SysRoleMenu>().eq("role_id", old.getId()));
            role.getMenuIds().stream().forEach(id -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(id).setRoleId(old.getId());
                sysRoleMenuDao.insert(roleMenu);
            });
        }else{
            //删除原来关联关系
            sysRoleMenuDao.delete(new EntityWrapper<SysRoleMenu>().eq("role_id", old.getId()));
        }
    }

    /**
     * 删除角色|可以批量删除
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String ids) {
        String[] idArr = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for(String id:  idArr){
            idList.add(Long.valueOf(id));
        }
        this.baseMapper.deleteBatchIds(idList);
    }
}
