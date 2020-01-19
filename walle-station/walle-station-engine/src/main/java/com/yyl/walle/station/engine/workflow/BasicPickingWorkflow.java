package com.yyl.walle.station.engine.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * author:yangyuanliang Date:2020-01-19 Time:15:07
 **/
@Component("BasicPickingWorkflow")
@Scope("prototype")
public class BasicPickingWorkflow extends WorkflowBase {
    protected static final Logger logger = LoggerFactory.getLogger(BasicPickingWorkflow.class);

    @Override
    public String getName() {
        return "BASIC PICKINGWORKFLOW";
    }

    @Override
    protected WorkflowStateBase onStateChanged(WorkflowStateBase state) {
        logger.debug("onStateChanged()");
        WorkflowStateBase nextState = WorkflowStateBase.Exit;
        try {
            if (state.equals(WorkflowStateBase.Start)) {
                nextState = onWorkflowStart();
            } else if (state.equals(PickingWorkflowState.PrintBills)) {
                nextState = onPrintBills();
            } else if (state.equals(WorkflowStateBase.Exception)) {
                nextState = onWorkflowException();
            } else if (state.equals(WorkflowStateBase.End)) {
                nextState = onWorkflowEnd();
            }
        } catch (Exception e) {

        }
        logger.debug("onStateChanged() done");
        return nextState;
    }

    public WorkflowStateBase onWorkflowStart() {
        logger.info("拣货流程");
        WorkflowStateBase state = PickingWorkflowState.PrintBills;
        try {
            logger.info("即将进入打印流程");
        }catch (Exception e){

        }
        return state;
    }

    public WorkflowStateBase onWorkflowEnd() {
        WorkflowStateBase state = PickingWorkflowState.Exit;
        return state;
    }

    public WorkflowStateBase onWorkflowException() {
        WorkflowStateBase state = PickingWorkflowState.End;
        try {
            logger.info("异常流程");
        }catch (Exception e){

        }
        return state;
    }

    private WorkflowStateBase onPrintBills() {
        WorkflowStateBase state = WorkflowStateBase.End;
        try {
            logger.info("打印流程");
            Thread.sleep(3000);
            logger.info("打印结束");

        } catch (Exception e) {

        }
        return state;
    }


}
