package com.my.controller;

import com.my.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Slf4j
public class OrderController3 {
    @Autowired private OrderServiceImpl3 orderServiceImpl3;
    @RequestMapping("/order/message1")
    public String message1() {
        //orderServiceImpl3.message();
        return "message1"; }
        @RequestMapping("/order/message2")
        public String message2() {
        //orderServiceImpl3.message();
        return "message2"; }
    @RequestMapping("/order/message")
    public String message() {
       return orderServiceImpl3.message("xx");
         }
}
