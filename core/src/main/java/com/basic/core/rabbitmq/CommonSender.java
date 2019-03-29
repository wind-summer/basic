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

    private RabbitTemplate rabbitTemplate;

    /**
     * 设置回调函数
     */
    public CommonSender(RabbitTemplate rabbitTemplate){
        //初始化设置回调函数
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback((correlationData, b, cause) -> {
            if (b) {
                log.info("消息id:{},消息成功消费!", correlationData);
            } else {
                log.error("消息id:{},消息消费失败,原因：{}", correlationData, cause);
            }
        });
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
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE_A, RabbitmqConfig.ROUTINGKEY_MSG, content, getCorrelationId());
    }

    /**
     * 单个发送邮箱消息
     */
    public void singleSendEmail(String content){
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE_A, RabbitmqConfig.ROUTINGKEY_EMAIL, content, getCorrelationId());
    }

}
