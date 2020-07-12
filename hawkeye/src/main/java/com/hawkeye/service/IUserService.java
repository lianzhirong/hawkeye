package com.hawkeye.service;

import com.github.pagehelper.PageInfo;
import com.hawkeye.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @since 2020-07-10
 */
public interface IUserService extends IService<User> {

    List<User> userList();

    PageInfo finduUerPage(Integer currentPage, Integer pageSize);

    List<User> findFirstData();

    List<User> findSecondData();

}
