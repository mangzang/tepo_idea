package com.lagou.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenu(Integer id) {

        List<Menu> list = menuMapper.findAllMenu(id);

        return list;


    }

    @Override
    public PageInfo<Menu> findAllMenu(PromotionAdVO promotionAdVO) {


        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu1();

        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {

        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }

    @Override
    public void saveMenu(Menu menu) {

        menuMapper.saveMenu(menu);
    }
}
