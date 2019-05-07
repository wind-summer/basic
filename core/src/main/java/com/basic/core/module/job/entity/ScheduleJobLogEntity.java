package com.basic.core.module.job.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.basic.core.mvc.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("schedule_job_log")
public class ScheduleJobLogEntity extends BaseEntity<ScheduleJobLogEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableField("job_id")
    private Long jobId;
    /**
     * spring bean名称
     */
    @TableField("bean_name")
    private String beanName;
    /**
     * 方法名
     */
    @TableField("method_name")
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;
    /**
     * 失败信息
     */
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}
