package com.my;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {
    @Autowired private RocketMQTemplate rocketMQTemplate;
    @Test
    public void testSyncSend(){
        SendResult result = rocketMQTemplate.syncSend("test-topic-1:tag", "这是一条同步消息");
        System.out.println(result);
    }
    @Test
    public void testAsyncSend() throws InterruptedException {
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("==============================");
        Thread.sleep(300000000);
    }
    @Test public void testOneWay() {
        for (int i = 0; i <10 ; i++) {
        rocketMQTemplate.sendOneWay("test-topic-1", "这是一条单向消息");}
    }
    @Test
    public void testOneWayOrderly(){
        for (int i = 0; i <10 ; i++) {
            rocketMQTemplate.sendOneWayOrderly("test-topic-1", "这是一条单向消息","xx");
        }
    }



}
