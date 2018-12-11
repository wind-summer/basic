package com.basic.core.listener;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *  我的自定义事件
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }
}
