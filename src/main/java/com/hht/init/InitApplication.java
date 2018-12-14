/**
 * Project Name:cluster-quartz
 * File Name:InitApplication.java
 * Package Name:com.hht.init
 * Date:2018年12月11日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.init;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hht.service.AreaDataService;
import com.hht.util.RedisUtil;
import com.hht.vo.Area;

/**
 * @author zhangguokang
 *
 * @description
 */
@Component
public class InitApplication implements ApplicationRunner {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(InitApplication.class);
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private AreaDataService areaDataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Area> listArea = areaDataService.listArea();
        redisUtil.set("AreaList", listArea);

    }

}
