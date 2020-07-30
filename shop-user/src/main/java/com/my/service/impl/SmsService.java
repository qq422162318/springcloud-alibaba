package com.my.service.impl;

import com.alibaba.fastjson.JSON;
import com.my.dao.UserDao;
import com.my.domain.Order;
import com.my.domain.User;
import com.my.utils.SmsUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "shop-user",
        topic = "order-topic",
        consumeMode = ConsumeMode.CONCURRENTLY,//消费模式默认同步 orderly顺序
        messageModel = MessageModel.BROADCASTING//消息模式 广播,集群
        )
public class SmsService implements RocketMQListener<Order> {
    @Autowired
    private UserDao userDao;
    //消费逻辑
    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息{},接下来就可以发送短信通知了",message);
        User user=userDao.findById(message.getUid()).get();
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i <6; i++) {
            builder.append( new Random().nextInt(9)+1);
        }
       String smsCode=builder.toString();
        Param param = new Param(smsCode);
        try {
            SmsUtil.sendSms(user.getTelephone(),"黑马旅游网","SMS_170836451", JSON.toJSONString(smsCode));
        log.info("success send message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Param{
        private String code;
    }
}
