package com.study;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
@EnableJms
public class JMSConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory  connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connectionFactory.setUserName(ActiveMQConnectionFactory.DEFAULT_USER);
        connectionFactory.setPassword(ActiveMQConnectionFactory.DEFAULT_PASSWORD);
        return  connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return  new JmsTemplate(connectionFactory());
    }



    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
      /*  factory.setConcurrency("3-10");
        factory.setRecoveryInterval(1000L);*/
        return  factory;
    }
}
