package com.basic.core.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.basic.core.mvc.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 系统数据字典
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionary")
public class SysDictionary extends BaseEntity<SysDictionary> {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    private Long pid;
    /**
     * 父名称
     */
    @TableField("dict_type")
    private String dictType;
    /**
     * 中文名称
     */
    @TableField("dict_name")
    private String dictName;
    /**
     * 字典编码
     */
    @TableField("dict_value")
    private String dictValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序号
     */
    @TableField("sort_no")
    private Integer sortNo;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
