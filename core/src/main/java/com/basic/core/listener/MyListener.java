package com.basic.core.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  我的事件监听器1
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
@Slf4j
@Component
public class MyListener /*implements ApplicationListener<MyEvent>*/ {
    @EventListener
    public void onApplicationEvent(MyEvent event) {
        log.info(String.format("%s监听到事件源：%s.", MyListener.class.getName(), event.getSource()));
        log.info("object对象：{}" + event.getSource().toString());
    }
}
