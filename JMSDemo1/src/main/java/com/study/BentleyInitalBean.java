package com.study;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class BentleyInitalBean implements InitializingBean {

    @Autowired
    private  Producer producer;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @Override
    public void afterPropertiesSet() throws Exception {

       executorService.scheduleWithFixedDelay(producer.senMessage(), 0,3, TimeUnit.SECONDS);

    }
}
