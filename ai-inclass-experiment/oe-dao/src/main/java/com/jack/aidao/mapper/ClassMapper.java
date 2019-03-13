package com.jack.aidao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.aidao.entity.ClassEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author wangwj
 * @data 2018/12/25
 */
@Repository
public interface ClassMapper extends BaseMapper<ClassEntity> {
    @Select("select * from class where class_id = #{classId}")
    ClassEntity getClassInfo(int classId);
}
