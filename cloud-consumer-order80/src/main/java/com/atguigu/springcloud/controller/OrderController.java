package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hobo
 * @create 2020-12-07 19:20
 */
@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";



    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return
                restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        return
                restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }
}
