package com.app;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * 注解配置
 *
 * @author chen.nie
 * @date 2018-12-12
 **/
@Configuration
@ComponentScan
@EnableTransactionManagement //开启事务管理
@MapperScan("com.app.Dao")
@PropertySource("classpath:application.properties")
public class AppConfig {


    @Bean
    public DataSource dataSource(PropertiesConfig config) {
        HikariDataSource dataSource = new HikariDataSource();
        //设置用户名
        dataSource.setUsername(config.getUserName());
        //设置密码
        dataSource.setPassword(config.getPassword());
        //设置驱动名
        dataSource.setDriverClassName(config.getDriverClassName());
        //设置连接字符串
        dataSource.setJdbcUrl(config.getJdbcUrl());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, PropertiesConfig config) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage(config.getMybatisTypeAliasedPackage());
        // 通过Spring的核心实现类获取mybatis的mapper映射文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(config.getMybatisMapperLocation());

        sqlSessionFactoryBean.setMapperLocations(resources);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor});


        return sqlSessionFactoryBean;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }


}
