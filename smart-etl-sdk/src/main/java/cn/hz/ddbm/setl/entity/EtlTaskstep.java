package cn.hz.ddbm.setl.entity;

import cn.hutool.json.JSONUtil;
import cn.hz.ddbm.setl.domain.Action;
import cn.hz.ddbm.setl.domain.Step;
import cn.hz.ddbm.setl.domain.StepType;
import cn.hz.ddbm.setl.domain.Task;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.util.StringUtils;

/**
 * (EtlTaskstep)实体类
 *
 * @author makejava
 * @since 2023-11-17 14:17:13
 */
@Data
@TableName("ETL_TASKSTEP")
public class EtlTaskstep implements Serializable {
    private static final long     serialVersionUID = 888541690116609515L;
    @TableId
    private              Integer  id;
    private              String   stepCode;
    private              String   taskCode;
    private StepType type;
    private              Integer  isCancel;
    private              Integer  isPause;
    private              String   attrsJson;
    private              String   memo;
    private              String   name;
    private              String   app;
    private              String   alias;


    public static EtlTaskstep of(Step step) {
        return null;
    }

    public Map<String, Object> getAttrs() {
        if (StringUtils.isEmpty(attrsJson)) {
            return Collections.emptyMap();
        }
        return JSONUtil.parseObj(attrsJson);
    }


    public static Step build(EtlTaskstep dto, Task task, Map<String, Action> actionMap) {
        return task.getTaskFactory().dtoToDomain(dto, task, actionMap);
    }


    public <T> T getAttr(String attrName, Class<T> type) {
        return (T) getAttrs().get(attrName);
    }
}

