package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.module.sys.dao.SysConfigDao;
import com.basic.core.module.sys.entity.SysConfig;
import com.basic.core.module.sys.redis.SysConfigRedis;
import com.basic.core.module.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {
    @Autowired
    private SysConfigRedis sysConfigRedis;

    /**
     * 分页查询
     *
     * @param page
     * @param key  配置编码
     * @return
     */
    @Override
    public Page<SysConfig> pages(Page<SysConfig> page, String key) {
        EntityWrapper ew = new EntityWrapper<SysConfig>();
        if(!StringUtils.isEmpty(key)){
            ew.like("code", key);
        }
        return this.selectPage(page, ew);
    }

    /**
     * 保存对象
     *
     * @param sysConfig
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysConfig sysConfig) {
        this.baseMapper.insert(sysConfig);
        sysConfigRedis.saveOrUpdate(sysConfig);
    }

    /**
     * 修改对象
     *
     * @param sysConfig
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfig sysConfig) {
        this.baseMapper.updateById(sysConfig);
        sysConfigRedis.saveOrUpdate(sysConfig);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids) {
        String[] idArr = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for(String id:  idArr){
            SysConfig sysConfig = this.baseMapper.selectById(Long.valueOf(id));
            sysConfigRedis.delete(sysConfig.getCode());
            idList.add(Long.valueOf(id));
        }
        this.baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据code获取对象
     *
     * @return
     */
    @Override
    public SysConfig getSysConfigByCode(String code) {
        return this.selectOne(new EntityWrapper<SysConfig>().eq("code", code));
    }
}
