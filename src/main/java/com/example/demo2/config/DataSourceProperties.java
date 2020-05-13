package com.example.demo2.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class DataSourceProperties {

    @Bean(name = "xframeDataSource")
    @Qualifier("xframeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.xframe")
    public DataSource xframeDataSource() {
        DataSource build = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return build;
    }

    @Bean(name = "baseDataSource")
    @Qualifier("baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource baseDataSource() {
        DataSource build = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return build;
    }
}
