package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Hobo
 * @create 2020-12-07 21:11
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }


    //超时后 降级方法
    //超时降级演示
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000") //5秒钟以内就是正常的业务逻辑
    })
    @Override
    public String payment_Timeout(Integer id) {
        //int timeNumber = 3; //小于等于3秒算是正常情况
        int timeNumber = 15; //模拟非正常情况
        //int i = 1/0 ; //模拟非正常情况
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" payment_Timeout,id="+id+" \t o(╥﹏╥)o 耗时："+timeNumber;
    }

    //兜底方法，上面方法出问题,我来处理，返回一个出错信息
    public String payment_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" payment_TimeoutHandler,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }



    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
                    commandProperties = {@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),    //是否开启熔断器
                                         @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //当在配置时间窗口内达到此数量的失败后，打开断路，默认20个
                                         @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //断路多久以后开始尝试是否恢复，默认5s
                                         @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //出错百分比阈值，当达到此阈值后，开始短路。默认50%
    })
    @Override
    public String PaymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("ID不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();    //HuTool工具包

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }


    //熔断兜底方法
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }






}