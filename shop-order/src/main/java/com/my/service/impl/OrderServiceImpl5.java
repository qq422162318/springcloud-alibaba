package com.my.service.impl;

import com.alibaba.fastjson.JSON;
import com.my.dao.OrderDao;
import com.my.domain.Order;
import com.my.domain.Product;
import com.my.service.OrderService;
import com.my.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl5  {

    @Autowired
    private OrderDao orderDao;
    @Autowired private RestTemplate restTemplate;
    @Autowired private ProductService productService;
    @Autowired private RocketMQTemplate rocketMQTemplate;
    //下单--ribbon负载均衡
    @RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        //扣减库存
        productService.reduceInventory(pid,order.getNumber());

        //(topic,body)
        rocketMQTemplate.convertAndSend("order-topic",order);

        return order;
    }



}
