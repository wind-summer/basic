package com.basic.core.module.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.basic.core.module.job.entity.ScheduleJobLogEntity;

/**
 * <p>
 * 定时任务日志 服务类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    /**
     * 分页查询
     * @param page
     * @param beanName bean名称
     * @param method   方法名称
     * @return
     */
    Page<ScheduleJobLogEntity> pages(Page<ScheduleJobLogEntity> page, String beanName, String method);
}
