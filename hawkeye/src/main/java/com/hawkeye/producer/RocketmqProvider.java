package com.hawkeye.producer;

import com.hawkeye.entity.Message;
import lombok.Data;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.UUID;

@Component
public class RocketmqProvider implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Override
    public void run(String... args) throws Exception {
        Message<String> message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setContent("Hello, springboot-ac-rocketmq !");
        rocketMQTemplate.convertAndSend("topic-queue-one", message);
        rocketMQTemplate.convertAndSend("topic-queue-two", "Hello, springboot-ac-rocketmq !");
    }

}


