package cn.hz.ddbm.setl.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hz.ddbm.setl.domain.*;
import cn.hz.ddbm.setl.service.TaskFactory;
import cn.hz.ddbm.setl.service.sdk.TaskService;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import cn.hz.ddbm.setl.config.EtlConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * (EtlTask)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ENTRY_TASK")
public class EntryTask implements Serializable {
    private static final long       serialVersionUID = 311928112251012978L;
    @TableId
    private              String     taskCode;
    private String   name;
    private TaskType type;
    private String   service;
    private              String     taskService;
    private              Object     cron;
    private              String     attrsJson;
    private              String     memo;
    private              String     app;
    private              String     alias;


    public static EntryTask of(Task workFlow) {
        return null;
    }


    public Boolean getFluent() {
        if (type.equals(TaskType.WORKFLOW)) {
            return JSONUtil.getByPath(getAttrs(), EtlConfig.COAST.FLUENT_ATTR, Boolean.TRUE);
        }
        return true;
    }

    public JSONObject getAttrs() {
        return JSONUtil.parseObj(attrsJson);
    }


    public static Task build(TaskFactory taskFactory,  TaskType type, EntryTask entryTask, List<EntryTaskstep> steps, List<EntryTaskstepAction> actions, ApplicationContext ctx) throws Exception {
        //构建
        Task                                       task      = new Task();
        Table<String, String, Map<String, Action>> actionMap = buildActions(task, actions, ctx);
        task.setType(type);
        task.setCode(entryTask.getTaskCode());
        task.setName(entryTask.getName());
        task.setFluent(entryTask.getFluent());
        task.setService(ctx.getBean(entryTask.getServiceOrDefault(), TaskService.class));
        task.setTaskFactory(taskFactory);
        task.setSteps(buildSteps(task, steps, actionMap));
        Step startStep = task.getSteps().values().stream().filter(step -> step.getType().equals(StepType.start)).findFirst().orElse(null);
        if (null == startStep) {
            throw new RuntimeException("WorkFlow.code[" + entryTask.getTaskCode() + "].startStep is null");
        }
        task.setStartStep(startStep);
        task.setFailStep(EtlConfig.FAIL_STEP);
        //合法性检查
        task.validate();
        return task;
    }

    private String getServiceOrDefault() {
        if (StringUtils.isEmpty(service)) {
            return EtlConfig.COAST.DEFAULT_SEREVICE;
        }
        return service;
    }

    private static Map<String, Step> buildSteps(Task flow, List<EntryTaskstep> steps, Table<String, String, Map<String, Action>> actions) {
        List<Step> taskSteps = steps.stream().map(step -> EntryTaskstep.build(step, flow, actions.get(flow.getCode(), step.getStepCode()))).collect(Collectors.toList());
        return taskSteps.stream().collect(Collectors.toMap(Step::getCode, s -> s));
    }

    private static Table<String, String, Map<String, Action>> buildActions(Task flow, List<EntryTaskstepAction> actions, ApplicationContext ctx) throws Exception {
        Table<String, String, Map<String, Action>> actionTables = HashBasedTable.create();
        for (EntryTaskstepAction action : actions) {
            Action              actionBean = EntryTaskstepAction.build(flow, action, ctx);
            Map<String, Action> actionMap  = actionTables.get(action.getTaskCode(), action.getTaskCode());
            if (null == actionMap) {
                synchronized (EntryTask.class) {
                    actionMap = new HashMap<>();
                    actionTables.put(action.getTaskCode(), action.getStepCode(), actionMap);
                }
            }
            actionTables.get(action.getTaskCode(), action.getStepCode()).put(action.getCommand().toLowerCase(), actionBean);
        }
        return actionTables;
    }

}

