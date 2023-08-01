package com.lagou.service.serviceImpl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAll(Role role) {

        List<Role> list = roleMapper.findAll(role);

        return list;

    }

    @Override
    public void saveRole(Role role) {

        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);

    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());

        roleMapper.updateRole(role);

    }

    @Override
    public List<Menu> findMenuByRoleId(Integer id) {

        List<Menu> menuByRoleId = roleMapper.findMenuByRoleId(id);
        return menuByRoleId;

    }

    //   // //描述: 为角色分配菜单
    @Override
    public void updateRoleMenu(RoleMenuVo roleMenuVo) {


        roleMapper.deleteRoleById(roleMenuVo.getRoleId());
        List<Integer> list = roleMenuVo.getMenuIdList();

        for (Integer menuId : list) {

            Date date = new Date();
            Role_menu_relation rm = new Role_menu_relation();
            rm.setUpdatedTime(date);
            rm.setCreatedTime(date);
            rm.setCreatedBy("system");
            rm.setUpdatedby("system");
            rm.setRoleId(roleMenuVo.getRoleId());
            rm.setMenuId(menuId);
            roleMapper.saveRoleMenu(rm);
        }

    }
}
