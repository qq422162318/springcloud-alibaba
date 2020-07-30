package com.my.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceImpl3Fallback {
    public String fallback(String name, Throwable e){
        log.error("触发了Throwable,内容为{}",e);
        return "Throwable";
    }
}
