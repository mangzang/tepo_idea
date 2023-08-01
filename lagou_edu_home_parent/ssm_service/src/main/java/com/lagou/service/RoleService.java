package com.lagou.service;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    //查询所有role
    public List<Role> findAll(Role role);

    //新增role
    public void saveRole(Role role);

    //更改role
    public void updateRole(Role role);

    //  <!-- //根据角色ID查询关联菜单ID-->
    public List<Menu> findMenuByRoleId(Integer id);


    // //描述: 为角色分配菜单
    public void updateRoleMenu(RoleMenuVo roleMenuVo);

}
