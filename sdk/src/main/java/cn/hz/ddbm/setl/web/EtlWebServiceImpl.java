package cn.hz.ddbm.setl.web;

import cn.hz.ddbm.setl.entity.EntryTask;
import cn.hz.ddbm.setl.entity.EntryTaskstep;
import cn.hz.ddbm.setl.entity.TaskLogs;
import cn.hz.ddbm.setl.entity.TaskStatus;
import cn.hz.ddbm.setl.exception.EtlException;
import cn.hz.ddbm.setl.exception.NotSupportFunctionException;
import cn.hz.ddbm.setl.service.EtlWebService;
import cn.hz.ddbm.setl.service.TaskFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hz.ddbm.setl.domain.EngineType;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.Task;
import cn.hz.ddbm.setl.mapper.EtlTaskLogsMapper;
import cn.hz.ddbm.setl.service.factory.AtomFactory;
import cn.hz.ddbm.setl.service.factory.PipelineFactory;
import cn.hz.ddbm.setl.service.factory.WorkFactory;

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
    public Page<EntryTask> findTasks(Predicate<EntryTask> predicate, Page<EntryTask> page) {
        List<EntryTask> datas = allTasks().stream().filter(predicate).sorted(Comparator.comparing(EntryTask::getTaskCode)).skip(page.getSize() * page.getCurrent()).limit(page.getSize()).collect(Collectors.toList());
        return page.setRecords(datas);
    }

    /**
     * @return
     */
    @Override
    public List<EntryTask> allTasks() {
        List<EntryTask> tasks = new ArrayList<>();
        tasks.addAll(workflowEtlService.allWorkflows().values().stream().map(EntryTask::of).collect(Collectors.toSet()));
        tasks.addAll(pipelineEtlService.allWorkflows().values().stream().map(EntryTask::of).collect(Collectors.toSet()));
        tasks.addAll(atomEtlService.allWorkflows().values().stream().map(EntryTask::of).collect(Collectors.toSet()));
        return tasks;
    }

    @Override
    public EntryTask getTask(String taskCode) {
        if (atomEtlService.allWorkflows().containsKey(taskCode)) {
            return EntryTask.of(atomEtlService.allWorkflows().get(taskCode));
        }
        if (pipelineEtlService.allWorkflows().containsKey(taskCode)) {
            return EntryTask.of(pipelineEtlService.allWorkflows().get(taskCode));
        }
        if (workflowEtlService.allWorkflows().containsKey(taskCode)) {
            return EntryTask.of(workflowEtlService.allWorkflows().get(taskCode));
        }
        throw new NoSuchElementException(taskCode);
    }

    @Override
    public List<EntryTaskstep> getSteps(String taskCode) {
        return workflowEtlService.allWorkflows().get(taskCode).getSteps().values().stream().map(EntryTaskstep::of).collect(Collectors.toList());
    }

    @Override
    public EntryTaskstep getStep(String taskCode, String stepCode) {
        Task flow = workflowEtlService.allWorkflows().get(taskCode);
        if (null == flow) {
            throw new NoSuchElementException(stepCode);
        }
        Step step = flow.getSteps().get(stepCode);
        if (null == step) {
            throw new NoSuchElementException(stepCode);
        }
        return EntryTaskstep.of(step);
    }

    @Override
    public void cancelTask(String taskId) throws EtlException, IOException, NotSupportFunctionException {
        TaskLogs taskLog = repository.selectById(taskId);
        if (taskLog.getEngineType().equals(EngineType.ATOM)) {
            throw new NotSupportFunctionException("任务ID:" + taskId + "类型为原子，不支持取消");
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
    public void pauseTask(String taskId) throws IOException, EtlException, NotSupportFunctionException {
        TaskLogs taskLog = repository.selectById(taskId);
        if (taskLog.getEngineType().equals(EngineType.ATOM)) {
            throw new NotSupportFunctionException("任务ID:" + taskId + "类型为原子，不支持取消");
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
