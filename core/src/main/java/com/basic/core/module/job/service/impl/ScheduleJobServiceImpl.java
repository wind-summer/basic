package com.basic.core.module.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.basic.core.module.job.dao.ScheduleJobDao;
import com.basic.core.module.job.entity.ScheduleJobEntity;
import com.basic.core.module.job.service.ScheduleJobService;
import com.basic.core.module.job.utils.ScheduleUtils;
import com.basic.core.utils.Constant;
import com.basic.core.utils.Constant.ScheduleStatus;
import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
@Service("ScheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobDao schedulerJobDao;

    /**
     * 分页查询
     *
     * @param page
     * @param beanName bean名称
     * @param method   方法名称
     * @return
     */
    @Override
    public Page<ScheduleJobEntity> pages(Page<ScheduleJobEntity> page, String beanName, String method) {
        EntityWrapper ew = new EntityWrapper<ScheduleJobEntity>();
        if(!StringUtils.isEmpty(beanName)){
            ew.like("bean_name", beanName);
        }
        if(!StringUtils.isEmpty(method)){
            ew.like("method", method);
        }
        return this.selectPage(page, ew);
    }

    /**
     * 保存
     *
     * @param scheduleJobEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ScheduleJobEntity scheduleJobEntity) {
        scheduleJobEntity.setCreateTime(new Date());
        scheduleJobEntity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        schedulerJobDao.insert(scheduleJobEntity);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobEntity);
    }

    /**
     * 修改
     *
     * @param scheduleJobEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJobEntity) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJobEntity);
        schedulerJobDao.updateById(scheduleJobEntity);
    }

    /**
     * 立即执行任务
     *
     * @param jobIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, selectById(jobId));
        }
    }

    /**
     * 暂停任务
     *
     * @param jobIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        this.update(new ScheduleJobEntity().setStatus(ScheduleStatus.PAUSE.getValue()), new EntityWrapper<ScheduleJobEntity>().in("id", jobIds));
        //updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    /**
     * 恢复任务
     *
     * @param jobIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        this.update(new ScheduleJobEntity().setStatus(ScheduleStatus.PAUSE.getValue()), new EntityWrapper<ScheduleJobEntity>().in("id", jobIds));
        //updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }
}
