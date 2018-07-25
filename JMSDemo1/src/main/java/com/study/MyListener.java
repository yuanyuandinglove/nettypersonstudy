package com.study;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @JmsListener(destination = "${activemq.heartbet.monitor} ",containerFactory = "jmsListenerContainerFactory")
   /// @JmsListener(destination = "activemq.dytest",containerFactory = "jmsListenerContainerFactory")
    public void receive(int message){
        System.out.println("监听到的消息："+ message);
    }
}
