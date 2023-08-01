package com.lagou.controller;


import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@Controller+@ResponseBody 返回值传给前端时转换为json串
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @RequestMapping("/findAll")
    public List<Test> findAll(){

        List<Test> all = testService.findAll();

        System.out.println(11);

        return all;

    }




}
