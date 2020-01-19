package com.yyl.walle.station.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * author:yangyuanliang Date:2020-01-17 Time:15:13
 **/
public class ThreadTest implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    StationTaskDispatcher stationTaskDispatcher=StationTaskDispatcher.getInstance();

    @Override
    public void run() {
        while (true){
            try {
                logger.info("产生任务");
                stationTaskDispatcher.onTaskMessage("101",new StationTask());
            } catch (Exception e) {
                e.printStackTrace();
            }
       }

    }
}
