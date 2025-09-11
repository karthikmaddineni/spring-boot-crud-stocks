package com.eureka.stockAnalytics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {
    //this look fpr spring.datasource in the application props

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }
    //this look fpr spring.datasource in the application props
    @Bean(name = "dataSourceCrud")
    @ConfigurationProperties(prefix = "spring.datasource-crudjpa")
    public DataSource getDataSourceCrud(){
        return DataSourceBuilder.create().build();
    }
}
