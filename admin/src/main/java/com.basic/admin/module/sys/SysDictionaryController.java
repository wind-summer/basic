package com.basic.admin.module.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.annotation.SysLog;
import com.basic.core.module.sys.entity.SysDictionary;
import com.basic.core.module.sys.service.SysDictionaryService;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  数据字典 前端控制器
 * </p>
 *
 * @author wenlongfei
 * @since 2019/4/28
 */
@RestController
@RequestMapping("/sys")
@AllArgsConstructor
@Api(description = "数据字典管理")
public class SysDictionaryController {
    private SysDictionaryService sysDictionaryService;

    @ApiOperation("配置字典")
    @GetMapping("/dictionary")
    public Page<SysDictionary> page(Page<SysDictionary> page, String dictType, String dictName, String dictValue, Long pid){
        Page<SysDictionary> pageList = sysDictionaryService.pages(page, dictType, dictName, dictValue, pid);
        return pageList;
    }

    /**
     * 保存配置
     */
    @SysLog("保存字典")
    @PostMapping("/dictionary")
    public ApiResult save(@RequestBody SysDictionary dictionary){
        sysDictionaryService.save(dictionary);
        return ApiResult.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PutMapping("/dictionary")
    public ApiResult update(@RequestBody SysDictionary dictionary){
        sysDictionaryService.update(dictionary);
        return ApiResult.ok();
    }

    @SysLog("修改配置")
    @ApiOperation("删除配置|批量删除")
    @DeleteMapping("/dictionary/{ids}")
    public ApiResult update(@PathVariable String ids){
        //sysDictionaryService.deleteBatch(ids);
        return ApiResult.ok("删除成功");
    }
}
