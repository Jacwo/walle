package com.yyl.walle.station.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * author:yangyuanliang Date:2020-01-17 Time:14:22
 **/
@Component
public class ServerProxy implements IServerProxy {
    private static final Logger logger = LoggerFactory.getLogger(ServerProxy.class);

    @Override
    public void init() {
        logger.info("init");
    }

    @Override
    public void destroy() {
        logger.info("destroy");

    }

    @Override
    public void start() throws Exception {
        logger.info("start");

    }

    @Override
    public void stop() {
        logger.info("stop");

    }

    @Override
    public void setStationTaskListerner(IStationTaskListener stationTaskListener) {
        logger.info("setStationTaskListerner");
         StationTaskDispatcher.getInstance().setListener("101",stationTaskListener);
    }
}
