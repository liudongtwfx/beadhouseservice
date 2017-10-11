package com.beadhouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by beadhouse on 2017/1/13.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.beadhouse.DAO",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager")
public class JpaConfiguration {
}
