package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.basic.core.exception.BizException;
import com.basic.core.module.sys.entity.SysMenu;
import com.basic.core.module.sys.dao.SysMenuDao;
import com.basic.core.module.sys.entity.request.SysMenuAdd;
import com.basic.core.module.sys.entity.request.SysMenuUpdate;
import com.basic.core.module.sys.entity.response.ParentTree;
import com.basic.core.module.sys.service.SysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Map<Long, SysMenu> treeMap = new HashMap<>();
        //将所有的数据放到map，以ID为key，对象为value
        menus.forEach(m -> {
            treeMap.put(m.getId(), m);
        });
        //遍历menus并且将对象帮到到父节点下面
        menus.forEach(menu -> {
            if(menu.getParentId() != null){
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
        //只去一级菜单
        List<SysMenu> newMenus = new ArrayList<>();
        menus.forEach(menu -> {
            if(menu.getParentId() == null){
                newMenus.add(menu);
            }
        });
        return newMenus;
    }

    /**
     * 获取上级菜单模型
     */
    @Override
    public List<ParentTree> parentTrees() {
        List<SysMenu> list = this.baseMapper.selectList(new EntityWrapper<SysMenu>());
        return getParentTrees(list);
    }

    /**
     * 组装菜单
     * @param menus
     * @return
     */
    private List<ParentTree> getParentTrees(List<SysMenu> menus) {
        Map<Long, ParentTree> treeMap = new HashMap<>();
        //将所有的数据放到map，以ID为key，对象为value
        List<ParentTree> trees = new ArrayList<>();
        menus.forEach(m -> {
            ParentTree tree = new ParentTree();
            tree.setLabel(m.getName()).setId(m.getId()).setParentId(m.getParentId());
            trees.add(tree);
            treeMap.put(m.getId(), tree);
        });
        //遍历trees并且将对象帮到到父节点下面
        trees.forEach(tree -> {
            if(tree.getId() != null){
                ParentTree node = treeMap.get(tree.getParentId());
                if(node != null){
                    List<ParentTree> children = node.getChildren();
                    if(children != null){
                        children.add(tree);
                    }else{
                        children = new ArrayList<>();
                        children.add(tree);
                        node.setChildren(children);
                    }
                }
            }
        });
        //只去一级菜单
        List<ParentTree> newMenus = new ArrayList<>();
        trees.forEach(tree -> {
            if(tree.getParentId() == null){
                newMenus.add(tree);
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
        if(sysMenuAdd.getParentId() != null){
            //验证上级菜单是否有
            Integer count = this.baseMapper.selectCount(new EntityWrapper<SysMenu>().eq("id", sysMenuAdd.getParentId()));
            if(count == 0){
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
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenus(Long id) {
        Integer count = this.baseMapper.selectCount(new EntityWrapper<SysMenu>().eq("parent_id", id));
        if(count > 0){
            throw new BizException("该节点含有子节点，不能删除");
        }
        this.baseMapper.deleteById(id);
    }
}
