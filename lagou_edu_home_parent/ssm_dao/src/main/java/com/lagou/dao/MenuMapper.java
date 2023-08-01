package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    public List<Menu> findAllMenu(Integer id);


    //分页查询所有menu
    public List<Menu> findAllMenu1();


    //根据id查询菜单
    public Menu findMenuById(Integer id);


    //添加菜单
    public void saveMenu(Menu menu);

    //修改菜单
    public void updateMenu(Menu menu);


}
