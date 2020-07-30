package com.my.controller;

import com.alibaba.fastjson.JSON;
import com.my.domain.Order;
import com.my.domain.Product;
import com.my.service.OrderService;
import com.my.service.ProductService;
import com.my.service.impl.OrderServiceImpl4;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Slf4j
public class OrderController4 {
    @Autowired private RestTemplate restTemplate;
    @Autowired private OrderServiceImpl4 orderService;
    @Autowired private DiscoveryClient discoveryClient;
    @Autowired private ProductService productService;
    @Autowired private RocketMQTemplate rocketMQTemplate;
    //下单--ribbon负载均衡
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        Product product = productService.findByPid(pid);
        if (product.getPid()==-100){
            Order order = new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }

        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.createOrderBefore(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        //(topic,body)
        rocketMQTemplate.convertAndSend("order-topic",order);
        return order;
    }

}
