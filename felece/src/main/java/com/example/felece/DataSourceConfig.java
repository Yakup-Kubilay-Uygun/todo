package com.example.felece;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-54-75-225-52.eu-west-1.compute.amazonaws.com:5432/d84jgdm4nk85tr");
        dataSourceBuilder.username("uaosjejmjjyxyq");
        dataSourceBuilder.password("fe42304e6d707628622561e983191ea95081bf4643daea01c144a57ef8e79669");
        return dataSourceBuilder.build();
    }
}
