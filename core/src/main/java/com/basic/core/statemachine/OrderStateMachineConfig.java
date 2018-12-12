package com.basic.core.statemachine;

import com.basic.core.statemachine.entity.OrderEvents;
import com.basic.core.statemachine.entity.OrderStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
@Configuration
@EnableStateMachine
@Slf4j
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception{
        states
            .withStates()
                .initial(OrderStates.WAITPAY)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception{
        transitions
                //(1) 付款
                .withExternal()
                .target(OrderStates.WAITPAY)
                .target(OrderStates.WAITSHIP)
                .event(OrderEvents.PAY)
                .and()
                // (2) 发货
                .withExternal()
                .target(OrderStates.WAITSHIP)
                .target(OrderStates.WAITRECEIVE)
                .event(OrderEvents.SHIPPING)
                .and()
                // (3) 收货
                .withExternal()
                .target(OrderStates.WAITRECEIVE)
                .target(OrderStates.WAITCOMMENT)
                .event(OrderEvents.RECEIVE)
                .and()
                // (4) 评论
                .withExternal()
                .target(OrderStates.WAITCOMMENT)
                .target(OrderStates.FINISH)
                .event(OrderEvents.COMMENT)
                .and()
                // (5) 取消
                .withExternal()
                .target(OrderStates.WAITPAY)
                .target(OrderStates.CANCEL)
                .event(OrderEvents.CANCEL)
                .and()
                .withExternal();
    }
}
