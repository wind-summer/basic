package com.basic.core.mvc.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.basic.core.mvc.vm.PageVM;

/**
 * @author wenlongfei
 * @Description: 标识类，继承该类的RestController，返回数据统一使用ApiResult封装
 * @date 2018/9/23 上午9:39
 */
public abstract class AbstractApiResultController {
    /**
     * 转换前端分页信息
     * @param page
     * @return
     */
    protected PageVM toPageVM(Page<?> page){
        return new PageVM(page.getRecords(),page.getTotal(),page.getSize(),page.getCurrent());
    }

}
