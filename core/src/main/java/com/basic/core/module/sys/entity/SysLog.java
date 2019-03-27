package com.basic.core.module.sys.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.basic.core.mvc.entity.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author wenlongfei
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends BaseEntity<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 账户
     */
    private String username;
    /**
     * 操作人
     */
    private String operation;
    /**
     * 方法
     */
    private String method;
    /**
     * 参数
     */
    private String params;
    /**
     * 执行时长
     */
    private Long time;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

}
