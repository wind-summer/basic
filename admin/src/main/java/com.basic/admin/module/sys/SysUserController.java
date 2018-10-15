package com.basic.admin.module.sys;


import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.service.SysUserService;
import com.basic.core.mvc.controller.AbstractApiResultController;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wenlongfei
 * @since 2018-10-12
 */
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Api(description = "用户管理")
public class SysUserController extends AbstractApiResultController {

    private SysUserService sysUserService;

    @ApiOperation("添加用户")
    @PostMapping("/user")
    public ApiResult save(@Validated SysUserAdd sysUser){
        sysUserService.save(sysUser);
        return ApiResult.ok("添加成功");
    }

    @ApiOperation("修改用户")
    @PutMapping("/user")
    public void update(SysUser sysUser){
        //sysUserService.save(sysUser);
    }
}

