/**
 * Project Name:cluster-quartz
 * File Name:WeatherDataService.java
 * Package Name:com.hht.service
 * Date:2018年11月30日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.service;

/**
 * @author zhangguokang
 *
 * @description
 */
public interface WeatherDataService {
    /**
     * 根据城市名称查询天气数据
     * 
     * @param cityName
     * @return
     */
    String getDataByCityName(String cityName);

    /**
     * 根据城市ID查询天气数据
     * 
     * @param cityId
     * @return
     */
    String getDataByCityId(String cityId);

    /**
     * 根据城市ID保存天气数据
     * 
     * @param cityId
     * @return
     */
    void saveWeatherData(String cityId);

}
