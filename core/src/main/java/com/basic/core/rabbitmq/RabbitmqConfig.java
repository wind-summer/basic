package com.basic.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * <p>
 *  rabbitmq 配置类
 * </p>
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


    public static final String EXCHANGE_A = "my-mq-exchange_a";
    public static final String EXCHANGE_B = "my-mq-exchange_b";
    public static final String EXCHANGE_C = "my-mq-exchange_c";
    public static final String EXCHANGE_D = "my-mq-exchange_d";


    public static final String QUEUE_MSG_A = "queue_msg_a";
    public static final String QUEUE_MSG_B = "queue_msg_b";
    public static final String QUEUE_EMAIL_A = "queue_email_a";
    public static final String QUEUE_EMAIL_B = "queue_email_b";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";
    public static final String ROUTINGKEY_D = "spring-boot-routingKey_D";

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
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_A);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * HeadersExchange ：通过添加属性key-value匹配
     * @return
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_B);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * DirectExchange:按照routingkey分发到指定队列
     * routingkey 如果绑定多个不一样的queue，这些绑定的所有queue都会受到消息
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_C);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * TopicExchange:多关键字匹配
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        new CustomExchange("",ExchangeTypes.SYSTEM);
        return new TopicExchange(EXCHANGE_D);
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
     * 获取msg队列B
     * @return
     */
    @Bean
    public Queue queueMsgB() {
        //durable:true 队列持久
        return new Queue(QUEUE_MSG_B, true);
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
     * 获取email队列B
     * @return
     */
    @Bean
    public Queue queueEmailB() {
        //durable:true 队列持久
        return new Queue(QUEUE_EMAIL_B, true);
    }

    /**
     * 广播模式 Exchange_C
     * @return
     */
    @Bean
    public Binding bindingC() {
        return BindingBuilder.bind(queueMsgA()).to(directExchange()).with(RabbitmqConfig.ROUTINGKEY_A);
    }

    /**
     * 广播模式 Exchange_A
     * @return
     */
    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(queueEmailA()).to(fanoutExchange());
    }

    /**
     * 暂时没搞明白用法
     * headers模式 Exchange_B
     * @return
     */
    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueEmailA()).to(headersExchange()).where("1").exists();
    }

    /**
     * topic模式 Exchange_D
     * @return
     */
    @Bean
    public Binding bindingD(){
        return BindingBuilder.bind(queueEmailA()).to(topicExchange()).with(RabbitmqConfig.ROUTINGKEY_D);
    }











    /*@Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    *//**
     * 单一消费者
     * @return
     *//*
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    *//**
     * 多个消费者
     * @return
     *//*
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",int.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }*/





















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
