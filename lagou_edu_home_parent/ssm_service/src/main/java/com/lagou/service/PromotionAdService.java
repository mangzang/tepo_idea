package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    //分页查询
    public PageInfo findPromotionAd(PromotionAdVO promotionAdVO);


    //新建广告接口
    public void savePromotionAd(PromotionAd promotionAd);

    //更改广告接口
    public void updatePromotionAd(PromotionAd promotionAd);


    //根据id查询广告
    public PromotionAd findPromotionAdById(Integer id);

    //更改状态
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
