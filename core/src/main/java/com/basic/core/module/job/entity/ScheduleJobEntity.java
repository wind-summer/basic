package com.basic.core.module.job.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.basic.core.mvc.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("schedule_job")
public class ScheduleJobEntity extends BaseEntity<ScheduleJobEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * spring bean名称
     */
    @TableField("bean_name")
    @NotBlank(message="bean名称不能为空")
    private String beanName;
    /**
     * 方法名
     */
    @TableField("method_name")
    @NotBlank(message="方法名不能为空")
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    @TableField("cron_expression")
    @NotBlank(message="cron表达式不能为空")
    private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
