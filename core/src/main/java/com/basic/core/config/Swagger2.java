package com.basic.core.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenlf
 * @since 2018/6/19
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Autowired
    ApplicationProperties applicationProperties;

    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.my.admin.sys.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/forezp")
                .termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .build();
    }*/

    /**
     * 系统接口
     * /api.* [访问的路径匹配,如:SwaggerApiController.java @RequestMapping("/api/v1") 符合该路径匹配]
     * @return
     */
    @Bean
    public Docket sysApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Sys模块")
                .apiInfo(sysInfo())
                //.globalOperationParameters(setHeaderToken())
                // 选择那些路径和api会生成document
                .select()
                // 对所有@Api注解进行监控
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any()) // 对所有路径进行监控[指定匹配路径监控(PathSelectors.regex("/api.*"))]
                .paths(PathSelectors.regex("/sys.*"))
                .build()
                .securitySchemes(securitySchemes());
    }

    /**
     * sys接口配置
     * 确保以下配置的信息可用，否则不能访问
     * @return
     */
    private ApiInfo sysInfo() {
        return new ApiInfoBuilder()
                .title("Sys测试API文档")
                .description(applicationProperties.getSwagger().getDescription())
                .license(applicationProperties.getSwagger().getLicense())
                .version(applicationProperties.getSwagger().getVersion())
                .build();
    }

    /**
     * 系统接口
     * /api.* [访问的路径匹配,如:SwaggerApiController.java @RequestMapping("/api/v1") 符合该路径匹配]
     * @return
     */
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Demo模块")
                .apiInfo(demoInfo())
                //.globalOperationParameters(setHeaderToken())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/demo.*"))
                .build()
                .securitySchemes(securitySchemes());
    }

    /**
     * demo接口配置
     * 确保以下配置的信息可用，否则不能访问
     * @return
     */
    private ApiInfo demoInfo() {
        return new ApiInfoBuilder()
                .title("Demo测试API文档")
                .description(applicationProperties.getSwagger().getDescription())
                .license(applicationProperties.getSwagger().getLicense())
                .version(applicationProperties.getSwagger().getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(
                new ApiKey("Authorization", "token", "header"));
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }
}
