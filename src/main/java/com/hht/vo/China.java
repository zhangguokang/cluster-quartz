/**
 * Project Name:cluster-quartz
 * File Name:China.java
 * Package Name:com.hht.vo
 * Date:2018年12月11日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.vo;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangguokang
 *
 * @description
 */
@XmlRootElement(name = "China")
@XmlAccessorType(XmlAccessType.FIELD)
public class China {

    @XmlElement(name = "province")
    private List<Province> provinceList;

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

}
