package cn.hz.ddbm.setl.service;

import cn.hz.ddbm.setl.exception.EtlException;
import cn.hz.ddbm.setl.exception.NotSupportCommandException;
import cn.hz.ddbm.setl.exception.NotSupportFunctionException;
import cn.hz.ddbm.setl.entity.EntryTask;
import cn.hz.ddbm.setl.entity.EntryTaskstep;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface EtlWebService {
    /**
     * 任务定义清单
     *
     * @return
     */
    List<EntryTask> allTasks();

    EntryTask getTask(String taskCode);

    List<EntryTaskstep> getSteps(String taskCode);

    EntryTaskstep getStep(String taskCode, String stepCode);

    /**
     * 取消任务
     */
    void cancelTask(String taskId) throws NotSupportCommandException, EtlException, IOException, NotSupportFunctionException;

    /**
     * 暂停任务
     *
     * @param taskId
     */
    void pauseTask(String taskId) throws NotSupportCommandException, IOException, EtlException, NotSupportFunctionException;

    /**
     * 查询任务执行状态
     *
     * @param taskId
     * @param taskCode
     * @return
     */
    TaskFactory.EtlTaskStatus query(String taskId, String taskCode);

    class EtlTaskStatus implements Serializable {

    }
}
