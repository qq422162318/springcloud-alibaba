package com.my.controller;

import com.alibaba.fastjson.JSON;
import com.my.domain.Order;
import com.my.domain.Product;
import com.my.service.ProductService;
import com.my.service.impl.OrderServiceImpl4;
import com.my.service.impl.OrderServiceImpl5;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController5 {
    @Autowired private OrderServiceImpl5 orderService;
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        return orderService.createOrder(pid);
    }

}
