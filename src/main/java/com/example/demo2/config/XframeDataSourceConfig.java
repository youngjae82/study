package com.example.demo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "xframeEntityManagerFactory",
        transactionManagerRef = "xframeTransactionManager",
        basePackages = {"com.example.demo2.repository.xframe"}
)
public class XframeDataSourceConfig {
    @Autowired
    @Qualifier("xframeDataSource")
    private DataSource xframeDataSource;

    @Primary
    @Bean(name = "xframeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean xframeEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(xframeDataSource)
                .packages("com.example.demo2.model.xframe")
                .persistenceUnit("xframePU")
                .build();
    }

    @Primary
    @Bean(name = "xframeTransactionManager")
    public PlatformTransactionManager xframeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(xframeEntityManagerFactory(builder).getObject());
    }
}
