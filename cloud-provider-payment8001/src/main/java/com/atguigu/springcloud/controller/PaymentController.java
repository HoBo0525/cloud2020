package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hobo
 * @create 2020-12-07 18:46
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    int port;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("插入结果为" + i);
        if (i > 0 ){
            return
                    new CommonResult(200, "插入数据成功", i);
        }else {
            return
                    new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info(("查询结果为" + payment));
        if (payment != null){
            return
                    new CommonResult(200, "查询数据成功port:" + port, payment);
        }else{
            return
                    new CommonResult(444, "查询数据失败", null);
        }
    }


}
