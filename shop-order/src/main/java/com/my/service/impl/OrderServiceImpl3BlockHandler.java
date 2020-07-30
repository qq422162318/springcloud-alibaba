package com.my.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceImpl3BlockHandler {
    public static String blockHandler(String name, BlockException e){
        log.error("触发了BlockException,内容为{}",e);
        return "BlockException";
    }
}
