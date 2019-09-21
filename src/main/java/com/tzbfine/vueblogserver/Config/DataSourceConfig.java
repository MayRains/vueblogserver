package com.tzbfine.vueblogserver.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean(name = "druidDataSource")
    @Qualifier(value = "druidDataSource")
    @ConfigurationProperties(value = "spring.datasource.druid")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }
}
