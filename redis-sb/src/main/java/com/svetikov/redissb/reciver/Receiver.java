package com.svetikov.redissb.reciver;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch){
        this.latch=latch;
    }

    public void receiveMessage(String message){
        log.info("Received <"+message+">");
        latch.countDown();
    }
}
