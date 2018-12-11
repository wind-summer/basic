package com.basic.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.basic.core","com.basic.admin"})
@MapperScan("com.basic.core.module.**.dao")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        //ConfigurableApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        //装载监听
        //context.addApplicationListener(new MyListener());
    }
}
