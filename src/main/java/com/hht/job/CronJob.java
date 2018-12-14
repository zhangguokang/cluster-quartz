/**
 * Project Name:cluster-quartz
 * File Name:CronJob.java
 * Package Name:com.hht.job
 * Date:2018年11月29日
 * Copyright (c) 2018 深圳市鸿合创新信息技术 Inc.All Rights Reserved.
 */
package com.hht.job;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hht.util.ForkJoinTaskForSaveWeather;

/**
 * @author zhangguokang
 *
 * @description
 */
public class CronJob implements Job {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(CronJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("=========================定时任务每2分钟执行一次===============================");
        log.info("jobName=====:" + jobExecutionContext.getJobDetail().getKey().getName());
        log.info("jobGroup=====:" + jobExecutionContext.getJobDetail().getKey().getGroup());
        log.info("taskData=====:" + jobExecutionContext.getJobDetail().getJobDataMap().get("taskData"));

        try {

            long start = System.currentTimeMillis();
            // 引入fork/join并行计算
            // 生成一个池
            ForkJoinPool forkjoinPool = new ForkJoinPool();
            ForkJoinTaskForSaveWeather task = new ForkJoinTaskForSaveWeather();

            // 执行一个任务，将任务放入池中，并开始执行，并用Future接收
            ForkJoinTask<Integer> result = forkjoinPool.submit(task);
            // 检查任务是否已经抛出异常或已经被取消了
            if (task.isCompletedAbnormally()) {
                log.info("task exception" + task.getException());
            }
            Integer count = result.get();
            long end = System.currentTimeMillis();
            log.info("[exec-  " + "CronJob" + "\tUse time : " + (end - start) + " ms!]");
            log.info("save weatherData success count:" + count);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
