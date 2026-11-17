package com.example.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
// @EnableTransactionManagement：开启 Spring 注解事务管理
@EnableTransactionManagement
public class JdbcConfig {

    //  配置数据库连接池
    @Bean
    public DataSource dataSource() {
        // 创建并配置数据源对象 这里使用了 HikariCP 连接池。它是 Spring 项目中常用的数据库连接池
        HikariDataSource dataSource = new HikariDataSource();
        // 数据库驱动
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // 数据库 URL
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/crm_db");
        // 用户名
        dataSource.setUsername("root");
        // 密码
        dataSource.setPassword("123456");

        return dataSource;
    }

    // 创建 JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        // DataSourceTransactionManager：管理数据库事务。
        // DataSource：数据库连接来源。
        return new DataSourceTransactionManager(dataSource);
    }
}
