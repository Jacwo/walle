package com.yyl.walle.station.engine;

import com.yyl.walle.station.engine.workflow.BasicPickingWorkflow;
import com.yyl.walle.station.engine.workflow.IWorkflow;
import com.yyl.walle.station.engine.workflow.WorkflowContext;
import com.yyl.walle.station.engine.workflow.WorkflowRunMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * author:yangyuanliang Date:2020-01-17 Time:10:22
 **/
@Service
public class StationEngine implements IStationEngine, MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(StationEngine.class);
    @Autowired
    private IServerProxy serverProxy;
    @Autowired
    private BeanFactory beanFactory;
    private IWorkflow currentWorkflowInstance;
    private volatile boolean isWorkflowRunning = false;
    private Queue<WorkflowRunMessage> workflowQueue = new LinkedList<WorkflowRunMessage>();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void init() throws Exception {
        logger.info("init 消息服务");
        logger.info("init 代理服务");
        logger.info("init 电子标签服务");
        logger.info("init 灯服务");
        logger.info("init 打印服务");
        logger.info("init 扫描服务");
        logger.info("init 工作流服务");

    }

    @Override
    public void start() throws Exception {
        logger.info("start 消息服务");
        logger.info("start 代理服务");
        logger.info("start 电子标签服务");
        logger.info("start 灯服务");
        logger.info("start 打印服务");
        logger.info("start 扫描服务");
        logger.info("start 工作流服务");
        this.serverProxy.setStationTaskListerner(new IStationTaskListener() {

            @Override
            public void onTaskReceived(IStationTask task) {

                StationEngine.this.onTaskReceived(task);
                //tryPullStationTopTask();
            }
        });
    }

    private synchronized void onTaskReceived(IStationTask stationTask) {
        if (stationTask != null) {
            logger.info("开始执行任务", stationTask.getTaskID());
            executeTask(stationTask);
        }

    }

    private synchronized void tryPullStationTopTask() {

        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    //String stationID = getStation().getStationID();
                    IStationTask stationTask = new StationTask();

                    onTaskReceived(stationTask);
                    return null;
                } catch (Exception e) {
                    logger.error("Exception happens in tryPullStationTask()", e);
                    //exceptionReporter.reportException(e);
                    throw e;
                }
            }
        });
        this.executorService.execute(task);
        return;
    }

    private synchronized void executeTask(IStationTask task) {

        logger.debug("receive task : " + task.getTaskID());
        IWorkflow workflowInstance = beanFactory.getBean(BasicPickingWorkflow.class.getSimpleName(), IWorkflow.class);
        if (workflowInstance.accept(task)) {
            WorkflowRunMessage workflowRunMsg = new WorkflowRunMessage();
            workflowRunMsg.setWorkflowToRun(BasicPickingWorkflow.class.getSimpleName());
            workflowRunMsg.setStationTask(task);
            runWorkflow(workflowRunMsg);

        }

    }

    private void runWorkflow(WorkflowRunMessage workflowRunMsg) {
        synchronized (this) {
            if (this.isWorkflowRunning) {
                this.workflowQueue.add(workflowRunMsg);

            } else {
                IStationTask task = workflowRunMsg.getStationTask();

                currentWorkflowInstance = beanFactory.getBean(workflowRunMsg.getWorkflowToRun(), IWorkflow.class);
                WorkflowContext context = new WorkflowContextImpl(task);
                try {
                    currentWorkflowInstance.run(context);
                    this.isWorkflowRunning = false;
                    if(workflowQueue.size()>0){
                        logger.debug("Current workflow complete, pop one from the queue");
                        WorkflowRunMessage workflowRunMsg2 = this.workflowQueue.poll();

                        logger.debug("notify the WorkflowRunMessage");
                        runWorkflow(workflowRunMsg2);
                        this.isWorkflowRunning = true;
                    }
                    currentWorkflowInstance.destroy();


                    logger.debug(String.format("workflow done() : taskID = %s", task != null ? task.getTaskID() : "unknown"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void stop() throws Exception {
        logger.info("stop");

    }

    @Override
    public void destroy() throws Exception {
        logger.info("destroy");

    }

    @Override
    public void onMessage(Message message) {

    }
}
