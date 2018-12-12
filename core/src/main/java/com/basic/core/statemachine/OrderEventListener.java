package com.basic.core.statemachine;

import com.basic.core.statemachine.entity.OrderEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
@WithStateMachine
@Slf4j
@Component
public class OrderEventListener {

    @OnTransition(target = "WAITPAY")
    public void create(){
        log.info("订单创建，待支付");
    }

    @OnTransition(source = "WAITPAY", target = "WAITSHIP")
    public void pay(){
        log.info("订单付款，待发货");
    }

    @OnTransition(source = "WAITSHIP", target = "WAITRECEIVE")
    public void ship(){
        log.info("订单发货，待接收");
    }

    @OnTransition(source = "WAITRECEIVE", target = "WAITCOMMENT")
    public void receive(){
        log.info("订单接收，待评论");
    }

    @OnTransition(source = "WAITCOMMENT", target = "FINISH")
    public void comment(){
        log.info("订单评论，已完成");
    }

    @OnTransition(source = "WAITPAY", target = "CANCEL")
    public void cancel(){
        log.info("订单创建，取消操作");
    }
}
