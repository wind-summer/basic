package com.basic.admin.module.demo.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/6/10
 */
@RestController
@RequestMapping("/sys/email")
@Slf4j
@AllArgsConstructor
@Api(description = "Email示例")
public class EmailController {
    @Autowired
    JavaMailSender jms;

    @GetMapping("/send")
    public void sendEmail(String content){
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("wenlongfeia@163.com");
        //接收者
        mainMessage.setTo("wenlf@litsoft.com.cn");
        //发送的标题
        mainMessage.setSubject("嗨喽");
        //发送的内容
        mainMessage.setText(content);
        jms.send(mainMessage);
    }
}
