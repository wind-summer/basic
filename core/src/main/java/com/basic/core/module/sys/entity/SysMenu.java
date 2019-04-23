package com.basic.core.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.basic.core.mvc.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableField("parent_id")
    private Long parentId;
    private String name;
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 组件-对应router-view的name和router的conponent
     */
    private String component;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children;

}
