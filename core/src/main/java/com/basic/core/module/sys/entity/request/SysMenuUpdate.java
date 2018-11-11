package com.basic.core.module.sys.entity.request;

import com.basic.core.module.sys.constant.SysMenuType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wenlongfei
 * @since 2018/11/6
 */
@Data
@ApiModel(description = "菜单修改内容")
public class SysMenuUpdate {

    /**
     * ID
     */
    @ApiModelProperty("ID")
    @NotNull(message = "ID不能为空")
    private Long id;
    /**
     * 类型
     */
    @ApiModelProperty("类型:MENU:菜单，BUTTON:按钮")
    @NotNull(message = "类型不能为空")
    private SysMenuType type;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 上级菜单ID
     */
    @ApiModelProperty("上级菜单ID")
    @NotNull(message = "上级菜单ID不能为空")
    private Long parentId;

    /**
     * 路由
     */
    @ApiModelProperty("路由url")
    private String url;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 排序，从小到大
     */
    @ApiModelProperty("排序")
    private Integer orderNum;

    /**
     * 授权标识
     */
    @ApiModelProperty("授权标识")
    private String perms;
}
