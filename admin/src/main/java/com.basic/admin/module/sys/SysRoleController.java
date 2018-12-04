package com.basic.admin.module.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色管理器
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/4
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(description = "角色管理")
public class SysRoleController {
    private SysRoleService sysRoleService;

    @ApiOperation("角色列表")
    @GetMapping("/roles")
    public Page<SysUser> page(Page<SysRole> page, String roleName){
        Page<SysUser> pageList = sysRoleService.pages(page, roleName);
        return pageList;
    }

//    @ApiOperation("添加菜单")
//    @PostMapping("/menu")
//    public ApiResult save(@RequestBody @Validated SysMenuAdd sysMenu){
//        sysMenuService.addMenu(sysMenu);
//        return ApiResult.ok("添加成功");
//    }
//
//    @ApiOperation("修改菜单")
//    @PutMapping("/menu")
//    public ApiResult update(@RequestBody @Validated SysMenuUpdate sysMenu){
//        sysMenuService.updateMenu(sysMenu);
//        return ApiResult.ok("修改成功");
//    }
//
//    @ApiOperation("删除菜单|批量删除")
//    @DeleteMapping("/menu/{id}")
//    public ApiResult update(@PathVariable Long id){
//        sysMenuService.deleteMenus(id);
//        return ApiResult.ok("删除成功");
//    }
}
