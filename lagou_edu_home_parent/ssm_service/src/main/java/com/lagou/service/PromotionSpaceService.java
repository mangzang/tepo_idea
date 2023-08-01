package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    //查询所有promotionSpace
    public List<PromotionSpace> findAllPromotionSpace();

    //增加广告位置
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //更改广告位信息
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位置，根据id
    public PromotionSpace findPromotionSpaceById(Integer id);

}
