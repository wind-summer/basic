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
public enum OrderStates {
    WAITPAY(0,"代付款"),
    WAITSHIP(1,"待发货"),
    WAITRECEIVE(2,"待收货"),
    WAITCOMMENT(3,"待评价"),
    FINISH(4,"订单完成"),
    CANCEL(5,"订单取消");

    private Integer value;
    private String desc;

    OrderStates(Integer value, String desc) {
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
