package com.yyl.walle.station.ui;

import com.yyl.walle.station.engine.IStationEngine;
import com.yyl.walle.station.engine.ThreadTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * author:yangyuanliang Date:2020-01-17 Time:10:40
 **/
public class StationEngineAgent  implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(StationEngineAgent.class);
    private IStationEngine stationEngine;

    public void init(){
        try {
            ApplicationContext context = StationUISpringContext.getContext();
            stationEngine = context.getBean(IStationEngine.class);
            logger.debug("init station engine");
            stationEngine.init();
            logger.debug("正在注册工作站");
            logger.debug("登陆工作站");
            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            logger.debug("start station engine");
            stationEngine.start();
            logger.debug("start station engine done");
            ThreadTest threadTest =new ThreadTest();
            new Thread(threadTest).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message message) {

    }
}
