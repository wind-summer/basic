package com.basic.core.module.demo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.basic.core.mvc.entity.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * demo表
 * </p>
 *
 * @author wenlongfei
 * @since 2018-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("my_demo")
public class Demo extends BaseEntity<Demo> {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 备注
     */
    private String remark;

}
