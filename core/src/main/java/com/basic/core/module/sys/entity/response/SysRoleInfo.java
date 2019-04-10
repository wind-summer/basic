package com.basic.core.module.sys.entity.response;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *  角色详情，包含关联菜单对应的id
 * </p>
 *
 * @author wenlongfei
 * @since 2019/4/9
 */
@Data
@Accessors(chain = true)
public class SysRoleInfo {
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 选肿菜单ID集合，过滤掉父节点，
     * 如果父节点所有id都被选择，
     * 则list里面有父节点id，反之则没有
     */
    private List<Long> menuIds;

}
