package com.basic.core.module.sys.entity.request;

import com.basic.core.module.sys.constant.SysUserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户开关实体
 *
 * @author wenlongfei
 * @since 2018/11/6
 */
@Data
@ApiModel(description = "用户开关")
public class SysUserSwitch {
    /**
     * 用户Id
     */
    @ApiModelProperty("id")
    @NotNull(message="id不能为空")
    private Long id;

    /**
     * 用户状态
     */
    @ApiModelProperty("status")
    @NotNull(message="状态不能为空")
    private SysUserStatus status;
}
