package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //URL: http://localhost:8080/ssm-web/menu/findAllMenu
    //为角色分配菜单，查询所有菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = menuService.findAllMenu(-1);

        HashMap<String, Object> map = new HashMap<>();

        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询成功",map);

    }


    //分页查询所有的菜单
    // <http://localhost:8080/ssm-web/menu/findAllMenu1>
    @RequestMapping("/findAllMenu1")
    public ResponseResult findAllMenu(PromotionAdVO promotionAdVO){

        PageInfo<Menu> allMenu = menuService.findAllMenu(promotionAdVO);

        return new ResponseResult(true,200,"分页查询成功",allMenu);
    }

    //URL: http://localhost:8080/ssm-web/menu/findMenuInfoById
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        if (id==-1){

            List<Menu> menuList = menuService.findAllMenu(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"新建回显成功",map);

        }else{

            Menu menuById = menuService.findMenuById(id);
            List<Menu> allMenu = menuService.findAllMenu(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menuById);
            map.put("parentMenuList",allMenu);

            return new ResponseResult(true,200,"更改回显成功",map);

        }



    }


    //保存menu//更改menu
    //URL: <http://localhost:8080/ssm-web/menu/saveOrUpdateMenu
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        menuService.saveMenu(menu);
        return new ResponseResult(true,200,"保存菜单成功",null);

    }

}
