package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

//http://localhost:8080/ssm-web/role/findAllRole
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> all = roleService.findAll(role);

        return new ResponseResult(true,200,"查询成功",all);

    }


    //http://localhost:8080/ssm-web/role/saveOrUpdateRole
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){

        if (role.getId()==null){

            roleService.saveRole(role);
            return new ResponseResult(true,200,"保存角色成功","");
        }else {

            roleService.updateRole(role);
            return new ResponseResult(true,200,"更改角色成功","");

        }

    }


    //URL: http://localhost:8080/ssm-web/role/findMenuByRoleId?roleId=4
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Menu> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"查询成功",menuByRoleId);
    }


//URL: http://localhost:8080/ssm-web/role/RoleContextMenu
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.updateRoleMenu(roleMenuVo);

        return new ResponseResult(true,200,"更改成功",null);



    }



}
