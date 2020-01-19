package com.yyl.walle.station.engine;

/**
 * author:yangyuanliang Date:2020-01-17 Time:10:21
 **/
public interface IStationEngine {
    void init() throws Exception;
    void start() throws Exception;
    void stop() throws Exception;
    void destroy() throws Exception;
}
