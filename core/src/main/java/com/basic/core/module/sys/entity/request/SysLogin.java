package com.basic.core.module.sys.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登陆参数
 * Created by wenlf on 2018/10/15
 */
@Data
@ApiModel(description = "用户添加内容")
public class SysLogin {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message="用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message="密码不能为空")
    private String password;

    /**
     * 密码
     */
    @ApiModelProperty("验证码")
    private String captcha;
}
