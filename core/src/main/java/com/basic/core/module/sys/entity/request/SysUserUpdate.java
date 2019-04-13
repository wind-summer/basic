package com.basic.core.module.sys.entity.request;

import com.basic.core.module.sys.constant.SysUserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @NotNull(message="id不能为空")
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

    /**
     * 状态
     */
    @ApiModelProperty("状态：ENABLE：启用 DISABLE:禁用")
    private SysUserStatus status;

    /**
     * 分配的角色Id列表
     */
    @ApiModelProperty("分配的角色Id列表")
    private List<Long> roleIds;
}
