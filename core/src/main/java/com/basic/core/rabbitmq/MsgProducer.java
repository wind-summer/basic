package com.basic.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>
 *  消息生产者
 * </p>
 *
 * @author wenlongfei
 * @since 2019/3/27
 */
@Slf4j
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content) {
        //设置回调参数，只有设置了才能触发回调
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE_A, RabbitmqConfig.ROUTINGKEY_MSG, content, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String cause) {
        log.info(" 回调id:" + correlationData);
        if (b) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + cause);
        }
    }
}
