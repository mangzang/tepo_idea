package com.lagou.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findUserByCondition(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        List<User> list = userMapper.findUser(userVo);

        PageInfo<User> userPageInfo = new PageInfo<>(list);

        return userPageInfo;
    }


    //登录
    @Override
    public User findUserByPhone(User user) {

        User userByPhone = userMapper.findUserByPhone(user);
        String password = null;
        try {
            password = Md5.md5(user.getPassword(), Md5.md5key);
            if (userByPhone!=null&&userByPhone.getPassword().equalsIgnoreCase(password)){

                return userByPhone;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void saveUser(User user) {
        String pass = null;
        try {
            pass = Md5.md5(user.getPassword(), Md5.md5key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user1 = new User();
        user.setPassword(pass);
        user.setName("kk");
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());

        userMapper.saveUser(user);
    }

    @Override
    public ResponseResult getUserPermissions(Integer uid) {

        //1/根据用户id得到关联的角色role
        List<Role> roles = userMapper.findRoleByUserId(uid);

        ArrayList<Integer> list = new ArrayList<>();
        for (Role role : roles) {

            list.add(role.getId());
        }
        System.out.println(list);

        //2/根据id（-1）得到menu 父菜单 //去重
        List<Menu> parentMenu = userMapper.findMenuById(list);

        //3.根据pid 得到menu子菜单  //去重
        for (Menu menu : parentMenu) {

            List<Menu> subMenu = userMapper.findMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }


        //4/根据id得到resource ，可以传入list，通过1得到的id数组
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"成功",map);

    }


}
