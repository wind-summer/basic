package com.basic.core.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 系统配置
 * 需要配置系统变量
 * @author wenlf
 * @since 18-9-26
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final Async async = new Async();

    private final CorsConfiguration cors = new CorsConfiguration();

    private final Swagger swagger = new Swagger();

    @Data
    public static class Async {

        private int corePoolSize = 5;

        private int maxPoolSize = 50;

        private int queueCapacity = 10000;

        private String threadNamePrifix = "";
    }

    @Data
    public static class Swagger {
        /**
         * 标题
         */
        private String title;
        /**
         * 描述
         */
        private String description;
        /**
         * license
         */
        private String license;
        /**
         * 版本
         */
        private String version;
    }
}
