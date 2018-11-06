package com.basic.core.module.sys.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wenlongfei
 * @since 2018/11/6
 */
@Data
@ApiModel(description = "菜单添加内容")
public class SysMenuAdd {

    /**
     * 菜单名称
     */
    @ApiModelProperty("姓名")
    @NotBlank(message="姓名不能为空")
    private String name;

    /**
     * 上级菜单ID
     */
    @ApiModelProperty("上级菜单ID")
    private Long parentId;
}
