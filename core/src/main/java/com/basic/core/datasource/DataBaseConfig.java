package com.basic.core.datasource;

/**
 * @author wenlf
 * @since 18-9-25
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataBaseConfig {

    @Bean
    @Primary
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.multiple.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("slaveDataSourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.multiple.slave1")
    public DataSource slaveDataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSourceOne) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, masterDataSource);
        targetDataSources.put(DataSourceNames.SECOND, slaveDataSourceOne);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

/*    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slaveJdbcTemplateOne")
    public JdbcTemplate slaveJdbcTemplateOne(@Qualifier("slaveDataSourceOne")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }*/
}
