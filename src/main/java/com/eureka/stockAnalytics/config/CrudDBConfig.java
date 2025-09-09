package com.eureka.stockAnalytics.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"com.eureka.stockAnalytics.repository.crud"},
        entityManagerFactoryRef = "entityManagerFactoryCrud",
transactionManagerRef = "transactionManagerCrud")
@EntityScan(basePackages = {"com.eureka.stockAnalytics.entity.crud"})
public class CrudDBConfig {

    @Autowired
    DataSource dataSourceCrud;

    @Bean(name = "entityManagerFactoryCrud")
    public LocalContainerEntityManagerFactoryBean getlocalContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSourceCrud);
        emf.setPackagesToScan("com.eureka.stockAnalytics.entity.crud");
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        return emf;
    }
    @Bean(name = "transactionManagerCrud")
    public JpaTransactionManager getTransactionManager(@Qualifier("entityManagerFactoryCrud")
                                                       EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
