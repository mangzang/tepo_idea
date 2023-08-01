package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

public interface UserService {

    // 用户分页&条件查询
    public PageInfo findUserByCondition(UserVo userVo);

    //登录
    public User findUserByPhone(User user);

    //新增用户
    public void saveUser(User user);

    public ResponseResult getUserPermissions(Integer uid);

}
