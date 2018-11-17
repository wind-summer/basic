package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.basic.core.exception.BizException;
import com.basic.core.module.sys.entity.SysMenu;
import com.basic.core.module.sys.dao.SysMenuDao;
import com.basic.core.module.sys.entity.request.SysMenuAdd;
import com.basic.core.module.sys.entity.request.SysMenuUpdate;
import com.basic.core.module.sys.entity.response.TreeNode;
import com.basic.core.module.sys.service.SysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    private SysMenuDao sysMenuDao;

    /**
     * 查询所有的菜单
     */
    @Override
    public List<SysMenu> findAllMenus() {
        List<SysMenu> list = this.baseMapper.selectList(new EntityWrapper<SysMenu>());
        List<SysMenu> menuTrees = getMenuTrees(list);
        return menuTrees;
    }

    /**
     * 组装菜单
     * @param menus
     * @return
     */
    private List<SysMenu> getMenuTrees(List<SysMenu> menus) {
        //List<TreeNode> trees = new ArrayList<>();
        Map<Long, SysMenu> treeMap = new HashMap<>();
        menus.forEach(m -> {
            //TreeNode treeNode = new TreeNode();
            //BeanUtils.copyProperties(m, treeNode);
            //trees.add(treeNode);
            treeMap.put(m.getId(), m);
        });

        menus.forEach(menu -> {
            if(menu.getParentId() != 0){
                SysMenu node = treeMap.get(menu.getParentId());
                if(node != null){
                    List<SysMenu> children = node.getChildren();
                    if(children != null){
                        children.add(menu);
                    }else{
                        children = new ArrayList<>();
                        children.add(menu);
                        node.setChildren(children);
                    }
                }
            }
        });

        List<SysMenu> newMenus = new ArrayList<>();
        menus.forEach(menu -> {
            if(menu.getParentId() == 0){
                newMenus.add(menu);
            }
        });
        return newMenus;
    }

    /**
     * 添加菜单
     *
     * @param sysMenuAdd
     */
    @Override
    public void addMenu(SysMenuAdd sysMenuAdd) {
        if(!sysMenuAdd.getParentId().equals(0)){
            //验证上级菜单是否有
            Integer count = this.baseMapper.selectCount(new EntityWrapper<SysMenu>().eq("id", sysMenuAdd.getParentId()));
            if(count > 0){
                throw new BizException("没有对应的上级菜单！");
            }
        }
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuAdd, sysMenu);
        sysMenu.setType(sysMenuAdd.getType().getValue());
        this.baseMapper.insert(sysMenu);
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     */
    @Override
    public void updateMenu(SysMenuUpdate sysMenu) {
        SysMenu oldSysMenu = this.baseMapper.selectById(sysMenu.getId());
        if(oldSysMenu == null){
            throw new BizException("没有相应的菜单可以修改");
        }
        BeanUtils.copyProperties(sysMenu, oldSysMenu);
        oldSysMenu.setType(sysMenu.getType().getValue());
        this.baseMapper.updateById(oldSysMenu);
    }

    /**
     * 删除菜单 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteMenus(String ids) {
        String[] idArr = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for(String id:  idArr){
            idList.add(Long.valueOf(id));
        }
        //去除系统管理员，系统管理员不可以删除
        if(idList!=null && idList.size()>0){
            this.baseMapper.deleteBatchIds(idList);
        }
    }
}
