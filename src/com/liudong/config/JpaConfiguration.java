package com.liudong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by liudong on 2017/1/13.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.liudong.DAO",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager")
public class JpaConfiguration {
}
