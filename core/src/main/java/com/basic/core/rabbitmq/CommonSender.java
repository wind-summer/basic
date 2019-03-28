package com.basic.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/3/28
 */
@Slf4j
@Component
public class CommonSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 设置回调函数
     */
    public CommonSender(){
        //初始化设置回调函数
        /*this.rabbitTemplate.setConfirmCallback((correlationData, b, cause) -> {
            if (b) {
                log.info("消息id:{},消息成功消费!", correlationData);
            } else {
                log.error("消息id:{},消息消费失败,原因：{}", correlationData, cause);
            }
        });*/
    }

    /**
     * 获取消息id
     * @return
     */
    private CorrelationData getCorrelationId(){
        return new CorrelationData(UUID.randomUUID().toString());
    }

    /**
     * 发送短信消息
     */
    public void sendMsg(String content){
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_C, RabbitmqConfig.ROUTINGKEY_A, content, getCorrelationId());
    }

    /**
     * 单个发送邮箱消息
     */
    public void singleSendEmail(String content){
        rabbitTemplate.convertAndSend(content);
    }

    /**
     * 群发送邮箱消息
     */
    public void groupSendEmail(String content){

    }

}
