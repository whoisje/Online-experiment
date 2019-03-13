package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/6
 */
@Data
@TableName("hasfinished")
public class HasFinishedEntity implements BaseEntity {

    private String studentId;

    private String experimentId;

    private String code;

    private String feeling;

    private String codeResult;

    private int runStatus;

    private int status;

    public HasFinishedEntity mapToStatus(){
        HasFinishedEntity entity = new HasFinishedEntity();
        entity.setExperimentId(this.experimentId);
        entity.setStatus(this.status);
        return entity;

    }


}
