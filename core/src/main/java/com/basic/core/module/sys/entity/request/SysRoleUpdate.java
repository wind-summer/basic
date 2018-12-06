package com.basic.core.module.sys.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  菜单添加内容
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/5
 */
@Data
@ApiModel(description = "角色修改内容")
public class SysRoleUpdate {

    /**
     * 角色Id
     */
    @ApiModelProperty("id")
    @NotNull(message="id不能为空")
    private Long id;

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
     * 用户名
     */
    @ApiModelProperty("部门ID")
    private Long deptId;

    /**
     * 菜单ID集合
     */
    @ApiModelProperty("菜单ID集合")
    private List<Long> menuIds;
}
