package com.basic.admin.module.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.annotation.SysLog;
import com.basic.core.module.sys.entity.SysConfig;
import com.basic.core.module.sys.entity.SysRole;
import com.basic.core.module.sys.service.SysConfigService;
import com.basic.core.mvc.vm.PageVM;
import com.basic.core.utils.ApiResult;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.basic.core.mvc.controller.AbstractApiResultController;

import javax.annotation.PostConstruct;
import javax.management.Query;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统配置信息表 前端控制器
 * </p>
 *
 * @author wenlongfei
 * @since 2019-04-26
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(description = "系统配置管理")
public class SysConfigController extends AbstractApiResultController {
    private SysConfigService sysConfigService;

    @ApiOperation("配置列表")
    @GetMapping("/config")
    public Page<SysConfig> page(Page<SysConfig> page, String code){
        Page<SysConfig> pageList = sysConfigService.pages(page, code);
        return pageList;
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @PostMapping("/config")
    public ApiResult save(@RequestBody SysConfig config){
        sysConfigService.save(config);
        return ApiResult.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PutMapping("/config")
    public ApiResult update(@RequestBody SysConfig config){
        sysConfigService.update(config);
        return ApiResult.ok();
    }

    @SysLog("修改配置")
    @ApiOperation("删除配置|批量删除")
    @DeleteMapping("/config/{ids}")
    public ApiResult update(@PathVariable String ids){
        sysConfigService.deleteBatch(ids);
        return ApiResult.ok("删除成功");
    }
}

