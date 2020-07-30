package com.my.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl3 {
    int i=0;
    @SentinelResource( value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderServiceImpl3Fallback.class,
            fallback = "fallback" )
    public String message(String name) {
        i++;
        if (i%3==0){
            throw new RuntimeException();
        }

        return "message";
    }



}
