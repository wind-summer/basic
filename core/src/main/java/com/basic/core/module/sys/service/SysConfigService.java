package com.basic.core.module.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysConfig;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.sys.entity.SysRole;

/**
 * <p>
 * 系统配置信息表 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 分页查询
     * @param page
     * @param key 配置编码
     * @return
     */
    Page<SysConfig> pages(Page<SysConfig> page, String key);

    /**
     * 保存对象
     * @param sysConfig
     */
    void save(SysConfig sysConfig);

    /**
     * 修改对象
     * @param sysConfig
     */
    void update(SysConfig sysConfig);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String ids);

    /**
     * 根据code获取对象
     * @param code
     * @return
     */
    SysConfig getSysConfigByCode(String code);
}
