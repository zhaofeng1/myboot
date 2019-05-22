package com.zf.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author zhaofeng
 * @Date2019/5/20 14:49
 * @Version V1.0
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryHasoffer",
        transactionManagerRef = "transactionManagerHasoffer",
        basePackages = {"com.zf.web.dao.hasoffer"})//dao repository 目录
public class HasofferDataSourceConfig {

    //数据源
    @Autowired
    @Qualifier("hasofferSource")
    private DataSource hasofferSource;

    //jpa实体类
    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerHasoffer")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryHasoffer(builder).getObject().createEntityManager();
    }


    @Primary
    @Bean(name = "entityManagerFactoryHasoffer")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryHasoffer(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(hasofferSource).properties(getVendorProperties())
                .packages("com.zf.web.model.hasoffer")// entity实体类
                .persistenceUnit("hasofferPersistenceUnit").build();
    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Primary
    @Bean(name = "transactionManagerHasoffer")
    public PlatformTransactionManager transactionManagerHasoffer(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryHasoffer(builder).getObject());
    }
}
