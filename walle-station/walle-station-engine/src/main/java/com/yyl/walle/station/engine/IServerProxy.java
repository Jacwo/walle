package com.yyl.walle.station.engine;

/**
 * author:yangyuanliang Date:2020-01-17 Time:14:20
 **/
public interface IServerProxy {
    void init();
    void destroy();
    void start() throws Exception;
    void stop();
    void setStationTaskListerner(IStationTaskListener stationTaskListener);

}
