package com.hawkeye.controller;


import com.hawkeye.service.ISsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/ss")
public class SsController {

    @Autowired
    private ISsService ssService;

    @GetMapping("/find")
    public String find() {
        ssService.list();
        return "";
    }
}
