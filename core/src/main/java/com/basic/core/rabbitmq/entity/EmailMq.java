package com.basic.core.rabbitmq.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *  邮箱传输实体对象
 * </p>
 *
 * @author wenlongfei
 * @since 2019/3/28
 */
@Data
@Builder
@Accessors(chain = true)
public class EmailMq {
    /**
     * 发送邮箱地址的号码
     */
    private List<String> emailAddresses;

    /**
     * 邮件内容
     */
    private String msg;
}
