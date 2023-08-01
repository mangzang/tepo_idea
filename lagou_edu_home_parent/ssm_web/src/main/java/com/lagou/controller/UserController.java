package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //http://localhost:8080/ssm-web/user/findAllUserByPage
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findUserByCondition(userVo);

        return new ResponseResult(true,200,"分页查询成功",pageInfo);

    }

    // http://localhost:8080/ssm-web/user/login?phone=18512341234&password=123456
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){

        User userByPhone = userService.findUserByPhone(user);

        if (userByPhone!=null){

            HttpSession session = request.getSession();
            String uuid = UUID.randomUUID().toString();
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",uuid);
            map.put("user_id",userByPhone.getId());



            session.setAttribute("access_token",uuid);
            session.setAttribute("user_id",userByPhone.getId());
            session.setAttribute("user",userByPhone);
            return new ResponseResult(true,1,"登录成功",map);


        }else {

            return new ResponseResult(true,1,"用户名密码错误",null);

        }

    }

    // http://localhost:8080/ssm-web/user/saveUser?phone=18512341234&password=123456
    @RequestMapping("/saveUser")
    public ResponseResult saveUser(User user){

        userService.saveUser(user);
        return new ResponseResult(true,200,"新增成功",null);

    }


    //URL: http://localhost:8080/ssm-web/user/getUserPermissions
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        String session_token = (String) request.getSession().getAttribute("access_token");

        String header_token = request.getHeader("Authorization");

        System.out.println(header_token);
        System.out.println(session_token);

        if (session_token.equalsIgnoreCase(header_token)){

            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;

        }else {

            return new ResponseResult(false,400,"获取失败",null);
        }

    }

}//
