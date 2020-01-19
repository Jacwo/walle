package com.yyl.walle.station.ui;

import com.yyl.walle.station.engine.ThreadTest;

/**
 * author:yangyuanliang Date:2020-01-17 Time:10:51
 **/

public class StationUIMain {
    private static StationEngineAgent engineAgent;

    public static void main(String[] args) {

        engineAgent = new StationEngineAgent();
        engineAgent.init();

    }
}
