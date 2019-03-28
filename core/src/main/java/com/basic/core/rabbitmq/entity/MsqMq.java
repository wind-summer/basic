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
public class MsqMq {

    /**
     * 发送短信的号码
     */
    private List<String> phoneNumbers;

    /**
     * 信息内容
     */
    private String msg;
}
