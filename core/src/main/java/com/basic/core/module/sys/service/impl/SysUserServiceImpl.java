package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.config.ApplicationProperties;
import com.basic.core.exception.BizException;
import com.basic.core.module.sys.constant.SysUserStatus;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.dao.SysUserDao;
import com.basic.core.module.sys.entity.SysUserRole;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.entity.request.SysUserSwitch;
import com.basic.core.module.sys.entity.request.SysUserUpdate;
import com.basic.core.module.sys.service.SysUserRoleService;
import com.basic.core.module.sys.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.utils.CurrentUserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    private SysUserDao sysUserDao;
    private SysUserRoleService sysUserRoleService;
    private ApplicationProperties applicationProperties;

    /**
     * 获取系统配置的默认系统管理员
     * @return
     */
    public List<Long> getAdministratorIds(){
        List<Long> ids = applicationProperties.getSecurity().getAdminIds();
        return ids;
    }

    /**
     * 创建用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserAdd user) {
        //判断添加的账户已经存在
        Integer count = this.baseMapper.selectCount(new EntityWrapper<SysUser>().eq("username", user.getUsername()));
        if(count > 0 ){
            throw new BizException("该账户已存在，请更换账户");
        }

        SysUser newUser = new SysUser();
        BeanUtils.copyProperties(user,newUser);
        newUser.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(4);
        newUser.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        if(user.getStatus()!= null){
            newUser.setSalt(salt).setStatus(user.getStatus().getValue());
        }else{
            newUser.setSalt(salt).setStatus(SysUserStatus.ENABLE.getValue());
        }
        sysUserDao.insert(newUser);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(newUser.getId(), user.getRoleIds());
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser queryByUserName(String username) {
        List<SysUser> userList = this.baseMapper.selectList(new EntityWrapper<SysUser>().eq("username", username));
        return userList.size() > 0 ? userList.get(0) : null;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param usernameOrName 条件
     * @return
     */
    @Override
    public Page<SysUser> pages(Page<SysUser> page, String usernameOrName) {
        return page.setRecords(this.baseMapper.selectSysUserPages(page, usernameOrName, getAdministratorIds()));
    }

    /**
     * 删除用户
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED,propagation = Propagation.SUPPORTS)
    public void delete(String ids) {
        String[] idArr = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for(String id:  idArr){
            idList.add(Long.valueOf(id));
        }
        //去除系统管理员，系统管理员不可以删除
        idList.removeAll(getAdministratorIds());
        if(idList!=null && idList.size()>0){
            this.baseMapper.deleteBatchIds(idList);
        }
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    public void update(SysUserUpdate user) {
        List<Long> ids = getAdministratorIds();
        if(ids.contains(user.getId())){
            throw new BizException("该用户是系统管理员不能修改");
        }
        SysUser oldUser = this.baseMapper.selectById(user.getId());
        if(oldUser == null){
            throw new BizException("没有相应的用户可以修改");
        }
        BeanUtils.copyProperties(user, oldUser);
        oldUser.setStatus(user.getStatus() !=null ? user.getStatus().getValue() : SysUserStatus.ENABLE.getValue());
        this.baseMapper.updateById(oldUser);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(oldUser.getId(), user.getRoleIds());
    }

    /**
     * 修改个人密码
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        SysUser user = CurrentUserUtils.getLogin();
        String username = user.getUsername();

        //账号不存在、密码错误
        if(!user.getPassword().equals(new Sha256Hash(oldPassword, user.getSalt()).toHex())) {
            throw new BizException("原始密码不正确,修改失败");
        }
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(4);
        String password = new Sha256Hash(newPassword, salt).toHex();
        this.baseMapper.updatePassword(username, password, salt);
    }

    /**
     * 修改用户状态
     *
     * @param userSwitch
     */
    @Override
    public void userSwitch(SysUserSwitch userSwitch) {
        List<Long> ids = getAdministratorIds();
        log.info("测试ids:{}",ids);
    }

    /**
     * 根据id查询用户信息，包括角色
     *
     * @param id
     * @return
     */
    @Override
    public SysUser userInfo(Long id) {
        SysUser user = this.baseMapper.selectById(id);
        if(user == null){
            return null;
        }
        List<SysUserRole> userRoles = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id", user.getId()));
        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return user.setRoleIdList(roleIds);
    }
}
