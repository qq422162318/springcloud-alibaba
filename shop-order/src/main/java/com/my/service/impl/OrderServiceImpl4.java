package com.my.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.my.dao.OrderDao;
import com.my.dao.TxLogDao;
import com.my.domain.Order;
import com.my.domain.TxLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service

public class OrderServiceImpl4 {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TxLogDao txLogDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //发送半事务消息
   public void createOrderBefore(Order order){
       String txId = UUID.randomUUID().toString();
       rocketMQTemplate.sendMessageInTransaction(
               "tx_producer_group",
               "tx_topic",
               MessageBuilder.withPayload(order).setHeader("txId",txId).build(),order
       );
   }
   @Transactional
   public void createOrder(String txId,Order order){
       orderDao.save(order);

       TxLog txLog = new TxLog();
       txLog.setTxid(txId);
       txLog.setDate(new Date());

       txLogDao.save(txLog);
   }
}
