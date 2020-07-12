package com.hawkeye.mapper;

import com.hawkeye.config.datasource.TargetDataSource;
import com.hawkeye.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-07-10
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> userList();

    @TargetDataSource("firstAopDataSource")
    List<User> findFirstData();

    @TargetDataSource("secondAopDataSource")
    List<User> findSecondData();
}
