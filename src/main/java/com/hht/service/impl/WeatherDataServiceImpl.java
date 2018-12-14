/**
 * Project Name:cluster-quartz
 * File Name:WeatherDataServiceImpl.java
 * Package Name:com.hht.service.impl
 * Date:2018年11月30日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.service.impl;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hht.service.WeatherDataService;

/**
 * @author zhangguokang
 *
 * @description
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://www.weather.com.cn/data/";
    private static final long TIME_OUT = 1800L; // 1800s

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;

        return this.doGetWeahter(uri);
    }

    private String doGetWeahter(String uri) {
        String key = uri;
        String strBody = null;
        //ObjectMapper mapper = new ObjectMapper();
        String resp = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        // 先查缓存，缓存有的取缓存中的数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("Redis don't has data");
            // 缓存没有，再调用服务接口来获取
            logger.info("uri:" + uri);
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }

            // 数据写入缓存
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
        }

        try {

            logger.info("strBody:" + strBody);
            // resp = mapper.readValue(strBody, String.class);
            resp = strBody;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("Error!", e);
        }

        return resp;
    }

    /**
     * 把天气数据放在缓存
     * 
     * @param uri
     */
    @Override
    public void saveWeatherData(String cityId) {
        String uri = WEATHER_URI + "sk/" + cityId + ".html";
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

    }

    @Override
    public String getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "sk/" + cityId + ".html";
        return this.doGetWeahter(uri);
    }

}
