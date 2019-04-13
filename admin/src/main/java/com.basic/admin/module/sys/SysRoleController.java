package com.basic.admin.module.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.entity.request.SysRoleAdd;
import com.basic.core.module.sys.entity.request.SysRoleUpdate;
import com.basic.core.module.sys.entity.response.RoleDrop;
import com.basic.core.module.sys.entity.response.SysRoleInfo;
import com.basic.core.module.sys.service.SysRoleService;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("角色下拉列表列表")
    @GetMapping("/role/droplist")
    public List<RoleDrop> droplist(){
        return sysRoleService.roleDropList();
    }

    @ApiOperation("角色列表")
    @GetMapping("/role/{id}/detail")
    public SysRoleInfo info(@PathVariable Long id){
        SysRoleInfo sysRoleInfo = sysRoleService.detail(id);
        return sysRoleInfo;
    }

    @ApiOperation("角色列表")
    @GetMapping("/roles")
    public Page<SysRole> page(Page<SysRole> page, String roleName){
        Page<SysRole> pageList = sysRoleService.pages(page, roleName);
        return pageList;
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public ApiResult save(@RequestBody @Validated SysRoleAdd sysRole){
        sysRoleService.addRole(sysRole);
        return ApiResult.ok("添加成功");
    }

    @ApiOperation("修改角色")
    @PutMapping("/role")
    public ApiResult update(@RequestBody @Validated SysRoleUpdate sysRole){
        sysRoleService.updateRole(sysRole);
        return ApiResult.ok("修改成功");
    }

    @ApiOperation("删除菜单|批量删除")
    @DeleteMapping("/role/{ids}")
    public ApiResult update(@PathVariable String ids){
        sysRoleService.deleteRoles(ids);
        return ApiResult.ok("删除成功");
    }
}
