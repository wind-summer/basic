package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.module.sys.dao.SysDictionaryDao;
import com.basic.core.module.sys.entity.SysConfig;
import com.basic.core.module.sys.entity.SysDictionary;
import com.basic.core.module.sys.service.SysDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统数据字典 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionary> implements SysDictionaryService {
    @Autowired
    private SysDictionaryDao sysDictionaryDao;
    /**
     * 分页
     *
     * @param page
     * @param dictName
     * @param dictType
     * @return
     */
    @Override
    public Page<SysDictionary> pages(Page<SysDictionary> page, String dictType, String dictName, String dictValue, Long pid) {
        EntityWrapper ew = new EntityWrapper<SysConfig>();
        if(pid != null){
            ew.eq("pid", pid);
        }else{
            ew.eq("pid", 0);
        }
        if(!StringUtils.isEmpty(dictType)){
            ew.like("dict_type", dictType);
        }
        if(!StringUtils.isEmpty(dictValue)){
            ew.like("dict_value", dictValue);
        }
        if(!StringUtils.isEmpty(dictName)){
            ew.like("dict_name", dictName);
        }
        return this.selectPage(page, ew);
    }

    /**
     * 新增
     *
     * @param dictionary
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictionary dictionary) {
        this.baseMapper.insert(dictionary);
    }

    /**
     * 修改
     *
     * @param dictionary
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictionary dictionary) {
        this.baseMapper.updateById(dictionary);
    }
}
