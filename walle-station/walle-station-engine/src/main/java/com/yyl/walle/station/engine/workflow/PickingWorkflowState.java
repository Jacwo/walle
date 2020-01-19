package com.yyl.walle.station.engine.workflow;

/**
 * author:yangyuanliang Date:2020-01-19 Time:15:32
 **/
public class PickingWorkflowState extends WorkflowStateBase {
    public static final WorkflowStateBase PrintBills = new WorkflowStateBase("PrintBills");

    public PickingWorkflowState(String value) {
        super(value);
    }

}
