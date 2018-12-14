/**
 * Project Name:cluster-quartz
 * File Name:QuartzDatasources.java
 * Package Name:com.hht.config
 * Date:2018年12月8日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangguokang
 *
 * @description
 */
@Configuration
public class QuartzDatasources {

    @Value("${spring.quartz.properties.driver}")
    private String driverClass;
    @Value("${spring.quartz.properties.URL}")
    private String jdbcUrl;

    @Value("${spring.quartz.properties.user}")
    private String username;

    @Value("${spring.quartz.properties.password}")
    private String password;

    @Value("${spring.quartz.properties.validationQuery}")
    private String validationQuery;

    public String getDriverClass() {

        return driverClass;
    }

    public String getJdbcUrl() {

        return jdbcUrl;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

}
