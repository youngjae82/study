package com.example.demo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "baseEntityManagerFactory",
        transactionManagerRef = "baseTransactionManager",
        basePackages = {"com.example.demo2.repository.base"}
)
public class BaseDataSourceConfig {

    @Autowired
    @Qualifier("baseDataSource")
    private DataSource baseDataSource;

    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(baseDataSource)
                .packages("com.example.demo2.model.base")
                .persistenceUnit("basePU")
                .build();
    }

    @Bean(name = "baseTransactionManager")
    public PlatformTransactionManager baseTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(baseEntityManagerFactory(builder).getObject());
    }
}
