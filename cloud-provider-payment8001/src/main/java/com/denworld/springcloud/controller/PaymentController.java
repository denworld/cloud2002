package com.denworld.springcloud.controller;

import com.denworld.springcloud.entities.CommonResult;
import com.denworld.springcloud.entities.Payment;
import com.denworld.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author denworld
 * @create 2022/11/2 15:22
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入結果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功，servicePort:" + serverPort, result);
        } else {
            return new CommonResult<>(1001, "插入数据库失败");
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功，servicePort:" + serverPort, payment);
        } else {
            return new CommonResult<>(1002, "沒有对应记录，查询ID：" + id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> list = discoveryClient.getServices();
        for (String element : list) {
            log.info("*****" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }
}
