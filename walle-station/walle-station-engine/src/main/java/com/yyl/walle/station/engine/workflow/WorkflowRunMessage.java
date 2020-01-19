package com.yyl.walle.station.engine.workflow;

import com.yyl.walle.station.engine.IStationTask;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * author:yangyuanliang Date:2020-01-19 Time:15:08
 **/
@Component
@Scope("prototype")
public class WorkflowRunMessage implements Serializable {
    private static final long serialVersionUID = -8100286831286369585L;

    private String workflowToRun;
    private IStationTask stationTask;

    public String getWorkflowToRun() {
        return workflowToRun;
    }

    public void setWorkflowToRun(String workflowToRun) {
        this.workflowToRun = workflowToRun;
    }

    public IStationTask getStationTask() {
        return stationTask;
    }

    public void setStationTask(IStationTask stationTask) {
        this.stationTask = stationTask;
    }
}
