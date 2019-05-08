package com.basic.core.module.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.job.entity.ScheduleJobEntity;
import com.basic.core.module.sys.entity.SysConfig;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 分页查询
     * @param page
     * @param beanName bean名称
     * @param methodName   方法名称
     * @return
     */
    Page<ScheduleJobEntity> pages(Page<ScheduleJobEntity> page, String beanName, String methodName);

    /**
     * 保存
     * @param scheduleJobEntity
     */
    void save(ScheduleJobEntity scheduleJobEntity);

    /**
     * 修改
     * @param scheduleJobEntity
     */
    void update(ScheduleJobEntity scheduleJobEntity);

    /**
     * 立即执行任务
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停任务
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复任务
     * @param jobIds
     */
    void resume(Long[] jobIds);

    /**
     * 删除任务
     * @param jobIds
     */
    void delete(String jobIds);
}
