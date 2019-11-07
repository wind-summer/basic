package com.basic.admin.module.demo.controller;


import com.basic.core.annotation.SysLog;
import com.basic.core.module.demo.entity.Demo;
import com.basic.core.module.demo.service.DemoService;
import com.basic.core.mvc.controller.AbstractApiResultController;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * demo表 前端控制器
 * </p>
 *
 * @author wenlongfei
 * @since 2018-09-26
 */
@RestController
@RequestMapping("/demo")
@Slf4j
@AllArgsConstructor
@Api(description = "DEMO示例")
public class DemoController extends AbstractApiResultController {

    private final DemoService demoService;

    @SysLog("Demo方法test执行")
    @GetMapping("/test")
    @Transactional(rollbackFor = Exception.class)
    public String test(){
        Demo demo = Demo.builder()
                .age(12)
                .name("马六")
                .remark("..")
                .build();
        demoService.insert(demo);
        log.info("好这个是demo 的测试方法：test");
        return "sssss";
    }
}

