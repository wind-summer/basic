package com.basic.admin.module.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.basic.core.module.demo.entity.Demo;
import com.basic.core.module.demo.service.DemoService;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.utils.CurrentUserUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.core.mvc.controller.AbstractApiResultController;

import java.util.List;

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
    private final ValueOperations valueOperations;

    @GetMapping("/test")
    @Transactional(rollbackFor = Exception.class)
    public void test(){
        SysUser user = CurrentUserUtils.getLogin();
        valueOperations.set("name","张三");
        List<Demo> demos = demoService.selectList(new EntityWrapper<Demo>().eq("id", 1L));
        /*Demo demo = new Demo();
        demo.setAge(12).setName("马六").setRemark("..");
        demoService.insert(demo);*/
        /*String test = "1s";
        Integer.valueOf(test);*/
        log.info("好这个是demo 的测试方法：test");
    }
}

