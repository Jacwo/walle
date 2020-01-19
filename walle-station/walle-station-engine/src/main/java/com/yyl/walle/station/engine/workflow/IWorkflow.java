package com.yyl.walle.station.engine.workflow;

import com.yyl.walle.station.engine.IStationTask;

/**
 * author:yangyuanliang Date:2020-01-17 Time:16:16
 **/
public interface IWorkflow {
    String getName();

    boolean accept(IStationTask task);
    void init();
    void run(WorkflowContext context) throws Exception;

    void destroy();
}
