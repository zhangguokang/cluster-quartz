/**
 * Project Name:cluster-quartz
 * File Name:JobService.java
 * Package Name:com.hht.service
 * Date:2018年11月29日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.service;

/**
 * @author zhangguokang
 *
 * @description 
 */
public interface JobService {
    /**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(String jobName, String jobGroup);

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    void addAsyncJob(String jobName, String jobGroup);

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     * @param triggerName
     * @param triggerGroup
     */
    void resumeJob(String triggerName, String triggerGroup);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);
}
