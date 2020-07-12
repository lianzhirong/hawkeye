package com.hawkeye.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hawkeye.entity.User;
import com.hawkeye.mapper.UserMapper;
import com.hawkeye.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> userList() {
        return userMapper.userList();
    }

    public PageInfo finduUerPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return  new PageInfo<User>(userMapper.userList());
    }

    @Override
    public List<User> findFirstData() {
        return userMapper.findFirstData();
    }

    @Override
    public List<User> findSecondData() {
        return userMapper.findSecondData();
    }
}
