package com.zsj.java_redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.java_redis.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
}
