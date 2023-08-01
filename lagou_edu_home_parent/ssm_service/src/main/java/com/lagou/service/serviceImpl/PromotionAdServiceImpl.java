package com.lagou.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;


    //分页显示
   @Override
    public PageInfo findPromotionAd(PromotionAdVO promotionAdVO) {


       //开启分页显示
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());


        //获取查询list
        List<PromotionAd> list = promotionAdMapper.findPromotionAdByPageHelper();

        //包装list，同时pageInfo对象包含分页的其它信息，如当前页码，页码可显示的条数，总页数等
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);

    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

       promotionAd.setUpdateTime(new Date());
       promotionAdMapper.updatePromotion(promotionAd);

    }

    @Override
    public PromotionAd findPromotionAdById(Integer id) {

        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);
        return promotionAdById;
    }

    @Override
    public void updatePromotionAdStatus(PromotionAd promotionAd) {

       promotionAd.setUpdateTime(new Date());
       promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }


}
