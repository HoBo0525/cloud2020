package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hobo
 * @create 2020-12-07 18:43
 */

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
