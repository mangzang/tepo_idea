package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {


    //查询所有广告位
    public List<PromotionSpace> findAllPromotionSpace();


    //添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);


    //增加广告位置
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位置
    public  PromotionSpace findPromotionSpaceById(Integer id);

}
