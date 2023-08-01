package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

//    /: 分页获取广告列表数据
    public List<PromotionAd> findPromotionAdByPageHelper();


    //新建广告接口
    public void savePromotionAd(PromotionAd promotionAd);


    //更改广告接口
    public void updatePromotion(PromotionAd promotionAd);


    //根据id查询
    public PromotionAd findPromotionAdById(Integer id);


    //更改状态
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
