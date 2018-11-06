package com.basic.admin.module.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysMenuAdd;
import com.basic.core.module.sys.entity.request.SysUserAdd;
import com.basic.core.module.sys.entity.request.SysUserUpdate;
import com.basic.core.module.sys.service.SysMenuService;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单管理
 * @author wenlongfei
 * @since 2018/11/6
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(description = "菜单管理")
public class SysMenuController {

    private SysMenuService sysMenuService;

    @ApiOperation("菜单列表")
    @GetMapping("/menus")
    public Page<SysUser> page(Page<SysUser> page, String usernameOrName){
        //Page<SysUser> pageList = sysUserService.pages(page, usernameOrName);
        return null;
    }

    @ApiOperation("添加菜单")
    @PostMapping("/menu")
    public ApiResult save(@RequestBody @Validated SysMenuAdd sysMenu){
        //sysUserService.save(sysUser);
        return ApiResult.ok("添加成功");
    }

    @ApiOperation("修改菜单")
    @PutMapping("/menu")
    public ApiResult update(@RequestBody @Validated SysUserUpdate sysUser){
        //sysUserService.update(sysUser);
        return ApiResult.ok("修改成功");
    }

    @ApiOperation("删除菜单|批量删除")
    @DeleteMapping("/menu/{ids}")
    public ApiResult update(@PathVariable String ids){
        //sysUserService.delete(ids);
        return ApiResult.ok("删除成功");
    }
}
