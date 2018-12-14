/**
 * Project Name:cluster-quartz
 * File Name:AsyncJob.java
 * Package Name:com.hht.job
 * Date:2018年11月29日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangguokang
 *
 * @description
 */
public class AsyncJob implements Job {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(AsyncJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       log.info("========================立即执行的任务，只执行一次===============================");
       log.info("jobName=====:" + jobExecutionContext.getJobDetail().getKey().getName());
       log.info("jobGroup=====:" + jobExecutionContext.getJobDetail().getKey().getGroup());
       log.info("taskData=====:" + jobExecutionContext.getJobDetail().getJobDataMap().get("asyncData"));
    }
}
