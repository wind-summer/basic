package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysDictionary;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.response.DictionaryDTO;

/**
 * <p>
 * 系统数据字典 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
public interface SysDictionaryService extends IService<SysDictionary> {

    /**
     * 分页
     * @param page
     * @param dictName
     * @param dictType
     * @return
     */
    Page<SysDictionary> pages(Page<SysDictionary> page, String dictType, String dictName, String dictValue, Long pid);

    /**
     * 新增
     * @param dictionary
     */
    void save(SysDictionary dictionary);

    /**
     * 修改
     * @param dictionary
     */
    void update(SysDictionary dictionary);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 根据字典类型查询字典
     * @param dictType
     * @return
     */
    DictionaryDTO findDictionariesByDictType(String dictType);
}
