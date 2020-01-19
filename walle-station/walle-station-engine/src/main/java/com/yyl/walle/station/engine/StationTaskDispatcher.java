package com.yyl.walle.station.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * author:yangyuanliang Date:2020-01-17 Time:15:02
 **/
public class StationTaskDispatcher {
    private static  StationTaskDispatcher stationTaskDispatcher=new StationTaskDispatcher();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static StationTaskDispatcher getInstance(){
        return stationTaskDispatcher;
    }
    private final Map<String, IStationTaskListener> stationListenerMap = new HashMap<String, IStationTaskListener>();
    public synchronized void setListener(String stationID, IStationTaskListener listener) {
        if(stationID == null){
            throw new IllegalArgumentException("argument 'stationID' cannot be null");
        }

        stationListenerMap.put(stationID, listener);
    }
    protected synchronized final void onTaskMessage(String stationID, IStationTask message) throws Exception{
        logger.debug(String.format("onTaskMessage() stationID: %s , task:%s", stationID, message));
        IStationTaskListener listener = stationListenerMap.get(stationID);
        if (listener != null) {
            listener.onTaskReceived((IStationTask)message);
        }

        logger.debug("onTaskMessage() done");
    }
}
