package com.example.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.example.core.config.properties.DruidProperties;
import com.example.core.metadata.CustomMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
@MapperScan(basePackages = {"**.mapper"})
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class MybatisDataSourceAutoConfiguration {
    @Autowired
    private DruidProperties druidProperties;

    /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        if (druidProperties.getUrl().contains("oracle")) {
            paginationInterceptor.setDialectType(DbType.ORACLE.getDb());
        } else if (druidProperties.getUrl().contains("postgresql")) {
            paginationInterceptor.setDialectType(DbType.POSTGRE_SQL.getDb());
        } else if (druidProperties.getUrl().contains("sqlserver")) {
            paginationInterceptor.setDialectType(DbType.SQL_SERVER.getDb());
        } else {
            paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        }
        return paginationInterceptor;
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 自定义公共字段自动注入
     */
    @ConditionalOnMissingBean
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

}
