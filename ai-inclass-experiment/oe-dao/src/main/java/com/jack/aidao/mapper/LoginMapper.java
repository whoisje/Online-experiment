package com.jack.aidao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.aidao.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Repository
public interface LoginMapper extends BaseMapper<UserEntity> {
}
