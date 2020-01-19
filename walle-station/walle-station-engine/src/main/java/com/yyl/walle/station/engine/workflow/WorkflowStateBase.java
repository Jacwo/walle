package com.yyl.walle.station.engine.workflow;

import java.io.Serializable;

/**
 * author:yangyuanliang Date:2020-01-19 Time:14:56
 **/
public class WorkflowStateBase implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final WorkflowStateBase Start = new WorkflowStateBase("Start");
    public static final WorkflowStateBase End = new WorkflowStateBase("End");
    public static final WorkflowStateBase Exit = new WorkflowStateBase("Exit");
    public static final WorkflowStateBase Exception = new WorkflowStateBase("Exception");
    public static final WorkflowStateBase Logout = new WorkflowStateBase("Logout");

    private String value;

    public WorkflowStateBase(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WorkflowStateBase) {
            WorkflowStateBase otherState = (WorkflowStateBase)other;
            return this.getValue().equalsIgnoreCase(otherState.getValue());
        }
        return false;
    }
}
