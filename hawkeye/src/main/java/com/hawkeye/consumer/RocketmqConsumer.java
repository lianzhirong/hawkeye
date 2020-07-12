package com.hawkeye.consumer;

import com.hawkeye.entity.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


@Service
public class RocketmqConsumer {


    @Service
    @RocketMQMessageListener(topic = "topic-queue-one", consumerGroup = "consumer_topic-queue-one")
    public class ConsumerOne implements RocketMQListener<Message> {
        @Override
        public void onMessage(Message message) {
            System.out.println("consumer-one received message: " + message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "topic-queue-two", consumerGroup = "consumer_topic-queue-two")
    public class ConsumerTwo implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("consumer-two received message: " + message);
        }
    }

}



