package com.basic.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 *  rabbitmq 配置类
 * </p>
 *
 * 说明：
 *      群发：使用FanoutExchange模式，如果想要做群发功能，就单独命名一个exchange名称，然后创建相应的queue(可以创建多个queue)
 *            ,然后binding所有的queue,发消息时指定exchange名称即可群发
 *      单点：可以使用DirectExchange, 指定一个exchange名称，然后指定一个queue，然后exchange绑定queue，然后发送，需要指定exchange和routingkey
 *
 *     topicExchange模式;比较复杂，简而言之就是用bindingkey，类似于java类通配符进行匹配，来进行监听
 *
 *      如果满足就会被监听到
 *     rabbitTemplate.send(TopicMqConfig.TOPIC_EXCHANGE_NAME, "quick.orange.male.rabbit", message);
 *     public static final String BINGDING_KEY_TEST1 = "*.orange.*";
 *
 * 	   public static final String BINGDING_KEY_TEST2 = "*.*.rabbit";
 *
 * 	   public static final String BINGDING_KEY_TEST3 = "lazy.#";
 *
 * @author wenlongfei
 * @since 2019/3/27
 */
@Slf4j
@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    public static final String DIRECT_EXCHANGE_A = "direct-exchange_a";
    public static final String DIRECT_EXCHANGE_B = "direct-exchange_b";
    public static final String EXCHANGE_C = "my-mq-exchange_c";


    public static final String QUEUE_MSG_A = "queue_msg_a";
    public static final String QUEUE_MSG_B = "queue_msg_b";
    public static final String QUEUE_EMAIL_A = "queue_email_a";
    public static final String QUEUE_EMAIL_B = "queue_email_b";

    public static final String ROUTINGKEY_MSG = "routingKey_msg";
    public static final String ROUTINGKEY_EMAIL = "routingKey_email";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 暂时先用directExchange交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_A);
    }

    /**
     * 获取msg队列A
     * @return
     */
    @Bean
    public Queue queueMsgA() {
        //durable:true 队列持久
        return new Queue(QUEUE_MSG_A, true);
    }

    /**
     * 获取email队列A
     * @return
     */
    @Bean
    public Queue queueEmailA() {
        //durable:true 队列持久
        return new Queue(QUEUE_EMAIL_A, true);
    }

    /**
     * 绑定queue_msg_a
     * directExchange模式 Direct_Exchange_A
     * @return
     */
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueMsgA()).to(directExchange()).with(RabbitmqConfig.ROUTINGKEY_MSG);
    }

    /**
     * 绑定queue_email_a
     * directExchange模式 Direct_Exchange_A
     * @return
     */
    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueEmailA()).to(directExchange()).with(RabbitmqConfig.ROUTINGKEY_EMAIL);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     *//*
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_A);
    }

    *//**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * HeadersExchange ：通过添加属性key-value匹配
     * @return
     *//*
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_B);
    }

    *//**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * DirectExchange:按照routingkey分发到指定队列
     * routingkey 如果绑定多个不一样的queue，这些绑定的所有queue都会受到消息
     * @return
     *//*
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_C);
    }

    *//**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * TopicExchange:多关键字匹配
     * @return
     *//*
    @Bean
    public TopicExchange topicExchange() {
        new CustomExchange("",ExchangeTypes.SYSTEM);
        return new TopicExchange(EXCHANGE_D);
    }

    *//**
     * 获取msg队列A
     * @return
     *//*
    @Bean
    public Queue queueMsgA() {
        //durable:true 队列持久
        return new Queue(QUEUE_MSG_A, true);
    }

    *//**
     * 获取msg队列B
     * @return
     *//*
    @Bean
    public Queue queueMsgB() {
        //durable:true 队列持久
        return new Queue(QUEUE_MSG_B, true);
    }

    *//**
     * 获取email队列A
     * @return
     *//*
    @Bean
    public Queue queueEmailA() {
        //durable:true 队列持久
        return new Queue(QUEUE_EMAIL_A, true);
    }

    *//**
     * 获取email队列B
     * @return
     *//*
    @Bean
    public Queue queueEmailB() {
        //durable:true 队列持久
        return new Queue(QUEUE_EMAIL_B, true);
    }

    *//**
     * 广播模式 Exchange_C
     * @return
     *//*
    @Bean
    public Binding bindingC() {
        return BindingBuilder.bind(queueMsgA()).to(directExchange()).with(RabbitmqConfig.ROUTINGKEY_A);
    }

    *//**
     * 广播模式 Exchange_A
     * @return
     *//*
    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(queueEmailA()).to(fanoutExchange());
    }

    *//**
     * 广播模式 Exchange_As
     * @return
     *//*
    @Bean
    public Binding bindingAS(){
        return BindingBuilder.bind(queueEmailB()).to(fanoutExchange());
    }

    *//**
     * 暂时没搞明白用法
     * headers模式 Exchange_B
     * @return
     *//*
    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueEmailA()).to(headersExchange()).where("1").exists();
    }

    *//**
     * topic模式 Exchange_D
     * @return
     *//*
    @Bean
    public Binding bindingD(){
        return BindingBuilder.bind(queueEmailA()).to(topicExchange()).with(RabbitmqConfig.ROUTINGKEY_D);
    }
*/
    /**
     *   1.定义direct exchange，绑定queueTest
     *   2.durable="true" rabbitmq重启的时候不需要创建新的交换机
     *   3.direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     *     fanout交换器中没有路由键的概念，他会把消息发送到所有绑定在此交换器上面的队列中。
     *     topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     *   key: queue在该direct-exchange中的key值，当消息发送给direct-exchange中指定key为设置值时，
     *   消息将会转发给queue参数指定的消息队列
     */
    /*@Bean
    public DirectExchange directExchange(){
        DirectExchange directExchange = new DirectExchange(RabbitMqConfig.EXCHANGE,true,false);
        return directExchange;
    }

    @Bean
    public Queue firstQueue() {
        *//**
         durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         exclusive  表示该消息队列是否只在当前connection生效,默认是false
         *//*
        return new Queue("first-queue",true,false,false);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("second-queue",true,false,false);
    }*/

}
