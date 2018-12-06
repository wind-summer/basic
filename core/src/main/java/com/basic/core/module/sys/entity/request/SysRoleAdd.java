package com.basic.core.module.sys.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 *  角色添加实体
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/5
 */
@Data
@ApiModel(description = "角色添加内容")
public class SysRoleAdd {

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @NotBlank(message="姓名不能为空")
    private String roleName;

    /**
     * 备注，说明
     */
    @ApiModelProperty("备注，说明")
    private String remark;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Long deptId;

    /**
     * 菜单ID集合
     */
    @ApiModelProperty("菜单ID集合")
    private List<Long> menuIds;
}
