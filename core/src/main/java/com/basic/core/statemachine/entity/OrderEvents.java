package com.basic.core.statemachine.entity;

import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/11
 */
@ToString
public enum OrderEvents {
    CREATE(0, "订单创建"),
    PAY(1, "付款"),
    SHIPPING(2, "发货"),
    RECEIVE(3, "收货"),
    COMMENT(4, "评论"),
    CANCEL(5, "取消订单");

    private Integer value;
    private String desc;

    OrderEvents(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
