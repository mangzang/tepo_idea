package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    //查询所有角色
    public List<Role> findAll(Role role);

    //新增角色
    public void saveRole(Role role);

    //更改角色
    public void updateRole(Role role);


    //根据角色ID查询关联菜单ID
    public List<Menu> findMenuByRoleId(Integer id);


    //描述: 为角色分配菜单
    public void saveRoleMenu(Role_menu_relation role_menu_relation);


    //根据角色id删除对应menu
    public void deleteRoleById(Integer id);



    //



}
