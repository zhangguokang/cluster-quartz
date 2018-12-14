/**
 * Project Name:cluster-quartz
 * File Name:CityDataServiceImpl.java
 * Package Name:com.hht.service.impl
 * Date:2018年12月11日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.hht.service.AreaDataService;
import com.hht.util.XmlBuilder;
import com.hht.vo.Area;
import com.hht.vo.China;
import com.hht.vo.City;
import com.hht.vo.Province;

/**
 * @author zhangguokang
 *
 * @description
 */
@Service
public class AreaDataServiceImpl implements AreaDataService {
    private final static Logger logger = LoggerFactory.getLogger(AreaDataServiceImpl.class);

    @Override
    public List<Area> listArea() throws Exception {
        List<Area> area_all = new ArrayList<Area>();
        // 读取XML文件
        Resource resource = new ClassPathResource("cityList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        br.close();

        // XML转为Java对象
        China china = (China) XmlBuilder.xmlStrToOject(China.class, buffer.toString());
        List<Province> provinceList = china.getProvinceList();
        for (Province province : provinceList) {
            List<City> cityList = province.getCityList();
            for (City city : cityList) {
                List<Area> areaList = city.getAreaList();
                for (Area area : areaList) {
                    //logger.info("area:" + area.getName());
                    area_all.add(area);
                }
            }
        }
        logger.info("area total:" + area_all.size());
        return area_all;

    }

}
