/**
 * Project Name:cluster-quartz
 * File Name:SchedulerConfiguration.java
 * Package Name:com.hht.config
 * Date:2018年11月29日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangguokang
 *
 * @description
 */
@Configuration
public class SchedulerConfiguration {

    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    private QuartzDatasources quartzDatasources;

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        // 获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));

        // 在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        // 创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Properties pro = propertiesFactoryBean.getObject();
        pro.setProperty("org.quartz.dataSource.qzDS.driver", quartzDatasources.getDriverClass());
        pro.setProperty("org.quartz.dataSource.qzDS.URL", quartzDatasources.getJdbcUrl());
        pro.setProperty("org.quartz.dataSource.qzDS.user", quartzDatasources.getUsername());
        pro.setProperty("org.quartz.dataSource.qzDS.password", quartzDatasources.getPassword());
        pro.setProperty("org.quartz.dataSource.qzDS.validationQuery", quartzDatasources.getValidationQuery());

        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setQuartzProperties(pro);
        factory.setJobFactory(myJobFactory);

        return factory;
    }

}
