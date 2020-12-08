package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hobo
 * @create 2020-12-07 22:10
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_TimeoutHandler2")
public class OrderHystrixController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return
                paymentHystrixService.paymentInfo_OK(id);
    }

    //超时演示
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",
                    commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return
                paymentHystrixService.paymentInfo_TimeOut(id);
    }
    //兜底方法
    public String payment_TimeoutHandler(Integer id){
        return
                "我是消费者80,对方支付系统繁忙请10秒后再试。或自己运行出错，请检查自己。" + id;
    }

    //全局兜底方法
    public String payment_TimeoutHandler2(Integer id){
        return
                "我是全局兜底方法" + id;
    }

}
