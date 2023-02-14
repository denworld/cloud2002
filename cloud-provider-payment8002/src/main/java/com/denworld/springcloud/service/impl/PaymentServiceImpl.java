package com.denworld.springcloud.service.impl;

import com.denworld.springcloud.dao.PaymentDao;
import com.denworld.springcloud.entities.Payment;
import com.denworld.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author denworld
 * @create 2022/11/2 15:15
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
