package com.basic.core.module.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.module.job.dao.ScheduleJobLogDao;
import com.basic.core.module.job.entity.ScheduleJobLogEntity;
import com.basic.core.module.job.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务日志 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    /**
     * 分页查询
     *
     * @param page
     * @param beanName bean名称
     * @param method   方法名称
     * @return
     */
    @Override
    public Page<ScheduleJobLogEntity> pages(Page<ScheduleJobLogEntity> page, String beanName, String method) {
        EntityWrapper ew = new EntityWrapper<ScheduleJobLogEntity>();
        if(!StringUtils.isEmpty(beanName)){
            ew.like("bean_name", beanName);
        }
        if(!StringUtils.isEmpty(method)){
            ew.like("method", method);
        }
        return this.selectPage(page, ew);
    }
}
