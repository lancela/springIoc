package com.example;


import com.example.config.JdbcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.example")
@EnableAspectJAutoProxy
//@Import 引入数据库配置。
@Import(JdbcConfig.class)
public class AppConfig {
}
