package com.yyl.walle.station.engine;

import com.yyl.walle.station.engine.workflow.WorkflowContext;

/**
 * author:yangyuanliang Date:2020-01-19 Time:15:18
 **/
public class WorkflowContextImpl implements WorkflowContext {
    private IStationTask stationTask;

    public WorkflowContextImpl( IStationTask stationTask){
        this.stationTask=stationTask;
    }
    @Override
    public IStationTask getStationTask() {
        return stationTask;
    }
}
