package com.basic.admin.module.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.annotation.SysLog;
import com.basic.core.module.job.entity.ScheduleJobEntity;
import com.basic.core.module.job.service.ScheduleJobService;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务
 * 
 * @author wenlf
 *
 * @date 2016年11月28日 下午2:16:40
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	@ApiOperation("定时任务列表")
	@GetMapping("/page")
	public Page<ScheduleJobEntity> page(Page<ScheduleJobEntity> page, String beanName, String methodName){
		Page<ScheduleJobEntity> pageList = scheduleJobService.pages(page, beanName, methodName);
		return pageList;
	}

	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@PostMapping("/save")
	public ApiResult save(@RequestBody ScheduleJobEntity scheduleJob){
		scheduleJobService.save(scheduleJob);
		return ApiResult.ok("保存成功");
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@PutMapping("/update")
	public ApiResult update(@RequestBody ScheduleJobEntity scheduleJob){
		scheduleJobService.update(scheduleJob);
		return ApiResult.ok("修改成功");
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@PutMapping("/run")
	public ApiResult run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		return ApiResult.ok("执行成功");
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@PutMapping("/pause")
	public ApiResult pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		return ApiResult.ok("暂停成功");
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@PutMapping("/resume")
	public ApiResult resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		return ApiResult.ok("恢复成功");
	}

    /**
     * 删除定时任务
     */
    @SysLog("删除定时任务")
    @DeleteMapping("/{jobIds}")
    public ApiResult delete(@PathVariable String jobIds){
        scheduleJobService.delete(jobIds);
        return ApiResult.ok("删除成功");
    }

}
