/**
 * Project Name:hht-backend-communication-websocket
 * File Name:SpringUtil.java
 * Package Name:com.hht.util
 * Date:2018年8月8日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.util;

/**
 * @author zhangguokang
 *
 * @description 获取spring bean工具类
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringUtil implements ApplicationContextAware {

     private static ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if(SpringUtil.applicationContext == null) {
                SpringUtil.applicationContext = applicationContext;
            }
        }

        //获取applicationContext
        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        //通过name获取 Bean.
        public static Object getBean(String name){
            return getApplicationContext().getBean(name);
        }

        //通过class获取Bean.
        public static <T> T getBean(Class<T> clazz){
            return getApplicationContext().getBean(clazz);
        }

        //通过name,以及Clazz返回指定的Bean
        public static <T> T getBean(String name,Class<T> clazz){
            return getApplicationContext().getBean(name, clazz);
        }

}
