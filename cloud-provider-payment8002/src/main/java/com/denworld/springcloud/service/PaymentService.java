package com.denworld.springcloud.service;

import com.denworld.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author denworld
 * @create 2022/11/2 14:50
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
