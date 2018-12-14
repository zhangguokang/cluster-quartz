/**
 * Project Name:cluster-quartz
 * File Name:CityDataService.java
 * Package Name:com.hht.service
 * Date:2018年12月11日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.service;

import java.util.List;

import com.hht.vo.Area;

/**
 * @author zhangguokang
 *
 * @description
 */
public interface AreaDataService {
    /**
     * 获取City列表
     * 
     * @return
     * @throws Exception
     */
    List<Area> listArea() throws Exception;
}
