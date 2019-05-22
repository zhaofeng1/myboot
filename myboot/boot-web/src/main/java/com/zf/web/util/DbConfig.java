package com.zf.web.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author zhaofeng
 * @Date2019/5/20 14:42
 * @Version V1.0
 **/
@Configuration
public class DbConfig {

    @Bean(name = "hasofferSource")
    @Qualifier("hasofferSource")
    @ConfigurationProperties(prefix = "spring.datasource.hasoffer")
    public DataSource hasofferSource() {
        return DataSourceBuilder.create().build();
    }
}
