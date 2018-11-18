package com.basic.core.module.sys.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *  上级菜单模型
 * </p>
 *
 * @author wenlongfei
 * @since 2018/11/18
 */
@Data
@Accessors(chain = true)
public class ParentTree {
    /**
     * value值
     */
    private Long id;
    /**
     * 标签值
     */
    private String label;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 子集
     */
    private List<ParentTree> children;
}
