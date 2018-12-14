package com.hht.util;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hht.service.WeatherDataService;
import com.hht.vo.Area;

//Recursive递归的意思,把大任务不断的拆分成小任务，即是一个递归拆分任务的一个过程
//RecursiveTask<T>,T表示任务的返回值

public class ForkJoinTaskForSaveWeather extends RecursiveTask<Integer> {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ForkJoinTaskForSaveWeather.class);

    private static final long serialVersionUID = 1L;
    // 设置分割的阈值
    public static final int threshold = 2;

    public volatile AtomicInteger inc = new AtomicInteger(0);
    private int start;
    private int end;
    private static List<Area> areaList;

    private static RedisUtil redisUtil = (RedisUtil) SpringUtil.getBean(RedisUtil.class);
    private WeatherDataService weatherDataService = (WeatherDataService) SpringUtil.getBean(WeatherDataService.class);

    static {
        List<Area> listArea = (List<Area>) redisUtil.get("AreaList");
        areaList = listArea;
    }

    public ForkJoinTaskForSaveWeather() {
        this.start = 0;
        this.end = areaList.size() - 1;
    }

    public ForkJoinTaskForSaveWeather(int start, int end) {
        this.start = start;
        this.end = end;

    }

    // 任务
    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            // 任务足够小的时候，直接计算，不进行分裂计算
            for (int i = start; i <= end; i++) {

                Area area = areaList.get(i);
                String weatherCode = area.getWeatherCode();
                weatherDataService.saveWeatherData(weatherCode);
                log.info("save weather data:" + weatherCode);
                // 保存成功,sum加1
                sum += 1;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;

            /**
             * 下面可能会产生递归操作
             */
            // 继续分裂任务
            ForkJoinTaskForSaveWeather leftTask = new ForkJoinTaskForSaveWeather(start, middle);
            ForkJoinTaskForSaveWeather rightTask = new ForkJoinTaskForSaveWeather(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;

        }
        return sum;

    }

}
