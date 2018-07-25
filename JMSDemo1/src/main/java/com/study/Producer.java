package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Producer {

    @Value("${activemq.heartbet.monitor}")
    public String destination;

    @Autowired
    private JmsTemplate jmsTemplate;


    public void convertAndSend(String destination, Integer message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    public Runnable senMessage() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                System.out.println(destination);
                convertAndSend(destination,  new Random().nextInt());
            }
        };
        return runnable;
    }

}
