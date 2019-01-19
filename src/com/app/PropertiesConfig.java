package com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取application.properites的文件内容
* @author chen.nie
* @date 2018-12-19
**/
@Component
public class PropertiesConfig {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.jdbcUrl}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${mybatis.typeAliases.package}")
    private String mybatisTypeAliasedPackage;

    @Value("${mybatis.mapper.location}")
    private String mybatisMapperLocation;

    @Value("${spring.web.upload.maxUploadSize}")
    private Integer maxFileUpload;

    public Integer getMaxFileUpload() {
        return maxFileUpload;
    }

    public String getMybatisMapperLocation() {
        return mybatisMapperLocation;
    }

    public String getMybatisTypeAliasedPackage() {
        return mybatisTypeAliasedPackage;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
