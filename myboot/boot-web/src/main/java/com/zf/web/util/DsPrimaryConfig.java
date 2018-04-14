package com.zf.web.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DsPrimaryConfig {

	@Bean(name = "primaryDS")
	@Qualifier("primaryDS")
	@Primary
	@ConfigurationProperties(prefix = "spring.primary.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

}