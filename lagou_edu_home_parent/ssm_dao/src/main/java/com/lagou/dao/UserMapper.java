package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    //查询所有用户
    public List<User> findUser(UserVo userVo);


    //登录
    public User findUserByPhone(User user);

    //新建用户
    public void saveUser(User user);


    //1/根据用户id得到关联的角色role
    public List<Role> findRoleByUserId(Integer id);

    //2/根据id（-1）得到menu 父菜单 //去重
    public List<Menu> findMenuById(List<Integer> ids);

    //3.根据pid 得到menu子菜单  //去重
    public List<Menu> findMenuByPid(Integer pid);

    //4/根据id得到resource ，可以传入list，通过1得到的id数组
    public List<Resource> findResourceByRoleId(List<Integer> ids);


}
