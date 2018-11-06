package com.basic.core.module.sys.entity.request;

import com.basic.core.module.sys.constant.SysUserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wenlongfei
 * @since 2018/11/6
 */
@Data
@ApiModel(description = "密码内容")
public class PasswordUpdate {

    /**
     * 用户原始密码
     */
    @ApiModelProperty("oldPassword")
    @NotBlank(message="原始密码不能为空")
    private String oldPassword;

    /**
     * 用户新密码
     */
    @ApiModelProperty("password")
    @NotBlank(message="新密码不能为空")
    private String password;
}
