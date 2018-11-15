package com.basic.core.module.sys.entity.response;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *  树形菜单
 * </p>
 *
 * @author wenlongfei
 * @since 2018/11/15
 */
@Data
public class TreeNode {

    /**
     * ID
     */
    private Long id;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 名称
     */
    private String name;
    /**
     * 路由
     */
    private String url;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<TreeNode> children;
}
