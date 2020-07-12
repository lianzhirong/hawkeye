package com.hawkeye.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.github.pagehelper.PageInfo;
import com.hawkeye.entity.User;
import com.hawkeye.service.IUserService;
import com.hawkeye.util.PageBean;
import com.hawkeye.util.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2020-07-10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public ResponseBean userList() {
        List<User> userList = userService.userList();
        return ResponseBean.success(userList);
    }

    @PostMapping("/user")
    public ResponseBean save(@RequestBody User user) {
        userService.save(user);
        return ResponseBean.success();
    }


    @DeleteMapping("/user/{id}")
    public ResponseBean delete(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return ResponseBean.success();
    }

    @PutMapping("/user")
    public ResponseBean update(@RequestBody User user) {
        userService.updateById(user);
        return ResponseBean.success();
    }

    @GetMapping("/userPage")
    public ResponseBean finduUerPage(@NotNull(message = "当前页码不能为空") Integer currentPage,
                                     @NotNull(message = "显示行数不能为空") Integer pageSize) {
        PageInfo pageInfo = userService.finduUerPage(currentPage, pageSize);
        return ResponseBean.success(new PageBean(pageInfo));
    }


    @RequestMapping("/firstAOPSelect")
    public List<User> findFirstData() {
        return userService.findFirstData();
    }

    @RequestMapping("/secondAOPSelect")
    public List<User> findSecondData() {
        return userService.findSecondData();
    }




}
