package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface MenuService {

    public List<Menu> findAllMenu(Integer id);

    //分页查询menu
    public PageInfo<Menu> findAllMenu(PromotionAdVO promotionAdVO);

    //根据id查询菜单
    public Menu findMenuById(Integer id);

    //保存
    public void saveMenu(Menu menu);

}
