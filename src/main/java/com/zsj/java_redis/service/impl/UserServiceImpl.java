package com.zsj.java_redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.java_redis.entity.User;
import com.zsj.java_redis.mapper.UserMapper;
import com.zsj.java_redis.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
