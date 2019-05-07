package com.basic.admin.module.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.job.entity.ScheduleJobLogEntity;
import com.basic.core.module.job.service.ScheduleJobLogService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/7
 */
@RestController
@RequestMapping("/sys/scheduleLog")
@AllArgsConstructor
public class ScheduleJobLogController {

    private ScheduleJobLogService scheduleJobLogService;

    @ApiOperation("定时任务列表")
    @GetMapping("/page")
    public Page<ScheduleJobLogEntity> page(Page<ScheduleJobLogEntity> page, String beanName, String method){
        Page<ScheduleJobLogEntity> pageList = scheduleJobLogService.pages(page, beanName, method);
        return pageList;
    }
}
