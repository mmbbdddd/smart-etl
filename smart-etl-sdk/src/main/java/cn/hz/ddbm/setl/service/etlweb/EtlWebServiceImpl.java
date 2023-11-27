package cn.hz.ddbm.setl.service.etlweb;

import cn.hz.ddbm.setl.entity.EtlTask;
import cn.hz.ddbm.setl.entity.EtlTaskstep;
import cn.hz.ddbm.setl.entity.TaskLogs;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.exception.EtlException;
import cn.hz.ddbm.setl.exception.NotSupportOperationException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hz.ddbm.setl.domain.EngineType;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import com.hundsun.etl.entity.*;
import cn.hz.ddbm.setl.mapper.EtlTaskLogsMapper;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.EtlWebService;
import cn.hz.ddbm.setl.service.sdk.factory.AtomFactory;
import cn.hz.ddbm.setl.service.sdk.factory.PipelineFactory;
import cn.hz.ddbm.setl.service.sdk.factory.WorkFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 页面工作流服务
 * 1，提供页面工作流crud,提供findTasks等接口
 * 2，工作流定义底层来源于db(pipeline,workflow)，和atom，乃至其他工厂定义。
 * 3，流程实例的状态变更等管理(tasklogs）——需要看具体工作流实现模式支持不支持。
 */
public class EtlWebServiceImpl implements EtlWebService {
    WorkFactory     workflowEtlService;
    PipelineFactory pipelineEtlService;
    AtomFactory     atomEtlService;

    EtlTaskLogsMapper repository;


    /**
     * 分页条件查询接口
     *
     * @param predicate
     * @return
     */
    public Page<EtlTask> findTasks(Predicate<EtlTask> predicate, Page<EtlTask> page) {
        List<EtlTask> datas = allTasks().stream().filter(predicate).sorted(Comparator.comparing(EtlTask::getTaskCode)).skip(page.getSize() * page.getCurrent()).limit(page.getSize()).collect(Collectors.toList());
        return page.setRecords(datas);
    }

    /**
     * @return
     */
    @Override
    public List<EtlTask> allTasks() {
        List<EtlTask> tasks = new ArrayList<>();
        tasks.addAll(workflowEtlService.allWorkflows().values().stream().map(EtlTask::of).collect(Collectors.toSet()));
        tasks.addAll(pipelineEtlService.allWorkflows().values().stream().map(EtlTask::of).collect(Collectors.toSet()));
        tasks.addAll(atomEtlService.allWorkflows().values().stream().map(EtlTask::of).collect(Collectors.toSet()));
        return tasks;
    }

    @Override
    public EtlTask getTask(String taskCode) {
        if (atomEtlService.allWorkflows().containsKey(taskCode)) {
            return EtlTask.of(atomEtlService.allWorkflows().get(taskCode));
        }
        if (pipelineEtlService.allWorkflows().containsKey(taskCode)) {
            return EtlTask.of(pipelineEtlService.allWorkflows().get(taskCode));
        }
        if (workflowEtlService.allWorkflows().containsKey(taskCode)) {
            return EtlTask.of(workflowEtlService.allWorkflows().get(taskCode));
        }
        throw new NoSuchElementException(taskCode);
    }

    @Override
    public List<EtlTaskstep> getSteps(String taskCode) {
        return workflowEtlService.allWorkflows().get(taskCode).getSteps().values().stream().map(EtlTaskstep::of).collect(Collectors.toList());
    }

    @Override
    public EtlTaskstep getStep(String taskCode, String stepCode) {
        Task flow = workflowEtlService.allWorkflows().get(taskCode);
        if (null == flow) {
            throw new NoSuchElementException(stepCode);
        }
        Step step = flow.getSteps().get(stepCode);
        if (null == step) {
            throw new NoSuchElementException(stepCode);
        }
        return EtlTaskstep.of(step);
    }

    @Override
    public void cancelTask(String taskId) throws NotSupportOperationException, EtlException, IOException {
        TaskLogs taskLog = repository.selectById(taskId);
        if (taskLog.getEngineType().equals(EngineType.ATOM)) {
            throw new NotSupportOperationException("任务ID:" + taskId + "类型为原子，不支持取消");
        } else {
//            工作流取消
            if (taskLog.getEngineType().equals(EngineType.PIPELINE)) {
                pipelineEtlService.updateTaskStatus(taskId, TaskStatus.cancel);
            }
            if (taskLog.getEngineType().equals(EngineType.PIPELINE)) {
                workflowEtlService.updateTaskStatus(taskId, TaskStatus.cancel);
            }
        }
//        数据库更新记录
        taskLog.setTaskStatus(TaskStatus.cancel);
        repository.updateById(taskLog);
    }

    @Override
    public void pauseTask(String taskId) throws NotSupportOperationException, IOException, EtlException {
        TaskLogs taskLog = repository.selectById(taskId);
        if (taskLog.getEngineType().equals(EngineType.ATOM)) {
            throw new NotSupportOperationException("任务ID:" + taskId + "类型为原子，不支持取消");
        } else {
//            工作流取消
            if (taskLog.getEngineType().equals(EngineType.PIPELINE)) {
                pipelineEtlService.updateTaskStatus(taskId, TaskStatus.pause);
            }
            if (taskLog.getEngineType().equals(EngineType.PIPELINE)) {
                workflowEtlService.updateTaskStatus(taskId, TaskStatus.pause);
            }
        }
        taskLog.setTaskStatus(TaskStatus.pause);
        repository.updateById(taskLog);
    }

    @Override
    public TaskFactory.EtlTaskStatus query(String taskId, String taskCode) {
        return null;
    }
}
