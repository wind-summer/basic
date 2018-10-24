package com.basic.core.module.sys.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by wenlf on 2018/10/12
 */
@Data
@ApiModel(description = "用户修改内容")
public class SysUserUpdate {

    /**
     * 用户Id
     */
    @ApiModelProperty("id")
    @NotBlank(message="id不能为空")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty("姓名")
    @NotBlank(message="姓名不能为空")
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Email(message="邮箱格式不正确")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;
}
