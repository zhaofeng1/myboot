package com.zf.web.util;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DbConfig {

	@Bean(name = "defaultDb")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

}
