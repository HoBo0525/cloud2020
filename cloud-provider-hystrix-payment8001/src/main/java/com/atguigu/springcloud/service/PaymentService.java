package com.atguigu.springcloud.service;

/**
 * @author Hobo
 * @create 2020-12-07 21:10
 */

public interface PaymentService {
    public String paymentInfo_OK(Integer id);
    public String payment_Timeout(Integer id);
    public String PaymentCircuitBreaker(Integer id);
}
