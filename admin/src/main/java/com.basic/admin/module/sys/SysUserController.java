package com.basic.admin.module.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.entity.request.SysUserUpdate;
import com.basic.core.module.sys.service.SysUserService;
import com.basic.core.mvc.controller.AbstractApiResultController;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("用户列表")
    @GetMapping("/users")
    public Page<SysUser> page(Page<SysUser> page, String usernameOrName){
        Page<SysUser> pageList = sysUserService.pages(page, usernameOrName);
        return pageList;
    }

    @ApiOperation("添加用户")
    @PostMapping("/user")
    public ApiResult save(@RequestBody @Validated SysUserAdd sysUser){
        sysUserService.save(sysUser);
        return ApiResult.ok("添加成功");
    }

    @ApiOperation("修改用户")
    @PutMapping("/user")
    public ApiResult update(@RequestBody @Validated SysUserUpdate sysUser){
        sysUserService.update(sysUser);
        return ApiResult.ok("修改成功");
    }

    @ApiOperation("删除用户|批量删除")
    @DeleteMapping("/user/{ids}")
    public ApiResult update(@PathVariable String ids){
        sysUserService.delete(ids);
        return ApiResult.ok("删除成功");
    }
}

