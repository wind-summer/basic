package com.basic.core.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.exception.BizException;
import com.basic.core.module.sys.dao.SysDictionaryDao;
import com.basic.core.module.sys.entity.SysConfig;
import com.basic.core.module.sys.entity.SysDictionary;
import com.basic.core.module.sys.entity.response.DictionaryDTO;
import com.basic.core.module.sys.service.SysDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 系统数据字典 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
@Service
@Slf4j
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionary> implements SysDictionaryService {

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
        if (pid != null) {
            ew.eq("pid", pid);
        } else {
            ew.eq("pid", 0);
        }
        if (!StringUtils.isEmpty(dictType)) {
            ew.like("dict_type", dictType);
        }
        if (!StringUtils.isEmpty(dictValue)) {
            ew.like("dict_value", dictValue);
        }
        if (!StringUtils.isEmpty(dictName)) {
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
        if (dictionary.getPid() != null) {
            List<SysDictionary> dictionaries = this.baseMapper.selectList(new EntityWrapper<SysDictionary>()
                    .eq("pid", dictionary.getPid() == null ? 0 : dictionary.getPid())
                    .eq("dict_value", dictionary.getDictValue()));
            if (dictionaries.size() > 0) {
                throw new BizException("字典项值" + dictionary.getDictValue() + "重复");
            }
        } else {
            List<SysDictionary> dictionaries = this.baseMapper.selectList(new EntityWrapper<SysDictionary>()
                    .eq("pid", 0)
                    .eq("dict_type", dictionary.getDictType()));
            if (dictionaries.size() > 0) {
                throw new BizException("类型值" + dictionary.getDictType() + "重复");
            }
        }
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
        for (String id : idArr) {
            Map<String, Object> param = new HashMap<>();
            param.put("pid", Long.valueOf(id));
            List<SysDictionary> dictionaries = this.baseMapper.selectByMap(param);
            if (dictionaries.size() > 0) {
                throw new BizException("id:" + id + "还有子项，请先删除子项");
            }
            idList.add(Long.valueOf(id));
        }
        this.baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据字典类型查询字典
     *
     * @param dictType
     * @return
     */
    @Override
    @Cacheable(value = "dictionaries", key = "#dictType")
    public DictionaryDTO findDictionariesByDictType(String dictType) {
        log.debug("Find dictionaries by dictType:{} from DB.", dictType);
        return Optional.ofNullable(selectOne(new EntityWrapper<SysDictionary>().eq("dict_type",dictType).eq("pid", 0)))
                .map(sysDictionary -> {
                    DictionaryDTO dictionaryDTO  = new DictionaryDTO();
                    dictionaryDTO.setDictType(sysDictionary.getDictType()).setDictName(sysDictionary.getDictName());
                    List<SysDictionary> children = selectList(new EntityWrapper<SysDictionary>()
                            .eq("pid", sysDictionary.getId()));
                    children.forEach(child -> dictionaryDTO.addDictionary(child));
                    return dictionaryDTO;
                }).orElse( null);
    }
}
