package com.basic.admin.module.demo.mqlistener;

import com.basic.core.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  msg消费者
 * </p>
 *
 * @author wenlongfei
 * @since 2019/3/27
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitmqConfig.QUEUE_A)
public class MsgReceiver {

    @RabbitHandler
    public void process(String content) {
        log.info("MsgReceiver接收处理队列A当中的消息： " + content);
    }
}
