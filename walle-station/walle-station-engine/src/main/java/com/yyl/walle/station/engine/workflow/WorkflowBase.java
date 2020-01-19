package com.yyl.walle.station.engine.workflow;

import com.yyl.walle.station.engine.IStationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author:yangyuanliang Date:2020-01-19 Time:14:51
 **/
public abstract class WorkflowBase implements IWorkflow {
    protected static final Logger logger = LoggerFactory.getLogger(WorkflowBase.class);

    protected IStationTask stationTask;
    protected WorkflowStateBase currentState;
    protected WorkflowStateBase previousState;
    @Override
    public boolean accept(IStationTask task) {
        return true;
    }

    @Override
    public void init() {
        logger.debug("init()");

    }

    @Override
    public void run(WorkflowContext context) throws Exception {
        logger.debug("run()");
        try {
            this.stationTask = context.getStationTask();
            WorkflowStateBase state=WorkflowStateBase.Start;
            while (true){
                this.currentState=state;
                state=onStateChanged(state);
                this.previousState = this.currentState;
                if (state == WorkflowStateBase.Exit) {
                    break;
                }
            }
        }catch (Exception e){

        }
    }

    protected  WorkflowStateBase onStateChanged(WorkflowStateBase state){
       // logger.debug("onStateChanged()");
        WorkflowStateBase nextState = WorkflowStateBase.Exit;
        try {
            if (state.equals(WorkflowStateBase.Start)) {
                nextState = onWorkflowStart();
            } else if (state.equals(WorkflowStateBase.Exception)) {
                nextState = onWorkflowException();
            } else if (state.equals(WorkflowStateBase.End)) {
                nextState = onWorkflowEnd();
            }
        } catch (Exception e) {
            logger.error("Exception happens in onStateChanged()", e);

            nextState = WorkflowStateBase.Exception;
        }

      //  logger.debug("onStateChanged() done");
        return nextState;
    }

    private WorkflowStateBase onWorkflowEnd() {
        logger.debug("onWorkflowEnd()");

        WorkflowStateBase state = WorkflowStateBase.Exit;
        try {
            // To be overridden
        } catch (Exception e) {
            logger.error("Exception happens in onWorkflowEnd()", e);

        }

        logger.debug("onWorkflowEnd() done");
        return state;
    }

    private WorkflowStateBase onWorkflowException() {
        logger.debug("onWorkflowException()");

        WorkflowStateBase state = this.previousState;
        try {

        } catch (Exception e) {
            logger.error("Exception happens in onWorkflowException()", e);

        }

        logger.debug("onWorkflowException() done");
        return state;
    }

    private WorkflowStateBase onWorkflowStart() {
        logger.debug("onWorkflowStart()");

        WorkflowStateBase state = WorkflowStateBase.End;
        try {
            // To be overridden
        } catch (Exception e) {
            logger.error("Exception happens in onWorkflowStart()", e);
        }

        logger.debug("onWorkflowStart() done");
        return state;
    }

    @Override
    public void destroy() {
        logger.debug("destroy()");

    }
}
