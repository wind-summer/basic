package com.basic.admin.module.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.basic.core.annotation.SysLog;
import com.basic.core.listener.MyEvent;
import com.basic.core.module.demo.entity.Demo;
import com.basic.core.module.demo.service.DemoService;
import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.rabbitmq.CommonSender;
import com.basic.core.rabbitmq.MsgProducer;
import com.basic.core.rabbitmq.RabbitmqConfig;
import com.basic.core.rabbitmq.entity.EmailMq;
import com.basic.core.statemachine.entity.OrderEvents;
import com.basic.core.statemachine.entity.OrderStates;
import com.basic.core.utils.CurrentUserUtils;
import com.basic.core.utils.SpringContextUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.core.mvc.controller.AbstractApiResultController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
    private StateMachine<OrderStates, OrderEvents> stateMachine;

    private MsgProducer msgProducer;
    private CommonSender commonSender;

    private RabbitTemplate rabbitTemplate;

    @SysLog("Demo方法test执行")
    @GetMapping("/test")
    @Transactional(rollbackFor = Exception.class)
    public String test(){
        /*stateMachine.start();
        stateMachine.sendEvent(OrderEvents.PAY);
        stateMachine.sendEvent(OrderEvents.SHIPPING);*/

        Demo demo = Demo.builder()
                .age(12)
                .name("马六")
                .remark("..")
                .build();
        demoService.insert(demo);

        //String str = "2j";
        //Integer i = Integer.valueOf(str);

        /*Demo demo = demoService.selectById(1);
        MyEvent event = new MyEvent(demo);
        SpringContextUtils.applicationContext.publishEvent(event);
        SysUser user = CurrentUserUtils.getLogin();
        valueOperations.set("name","张三");
        List<Demo> demos = demoService.selectList(new EntityWrapper<Demo>().eq("id", 1L));

        String test = "1s";
        Integer.valueOf(test);*/

        log.info("好这个是demo 的测试方法：test");
        return "sssss";
    }

    @GetMapping("/test1")
    @Transactional(rollbackFor = Exception.class)
    public void test2(){
        /*rabbitTemplate.setConfirmCallback((correlationData, b, cause) -> {
            log.info(" -----回调id:" + correlationData);
            if (b) {
                log.info("消息成功消费11111");
            } else {
                log.info("消息消费失败11111:" + cause);
            }
        });*/
        //rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_A, RabbitmqConfig.ROUTINGKEY_A, "你是什么地干活");
        //msgProducer.sendMsg("你好啊！！！");
        EmailMq emailMq = EmailMq.builder()
                .emailAddresses(Arrays.asList("wenlongfei_person@163.com", "wenlf@litsoft.com.cn"))
                .msg("你好啊，大兄弟！")
                .build();
        commonSender.sendMsg(JSON.toJSONString(emailMq));
        commonSender.singleSendEmail(JSON.toJSONString(emailMq));



    }
}

