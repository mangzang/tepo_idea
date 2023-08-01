package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;


//http://localhost:8080/ssm-web/PromotionSpace/findAllPromotionSpace
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();

        return new ResponseResult(true,200,"查询成功",list);

    }

//<http://localhost:8080/ssm-web/PromotionSpace/saveOrUpdatePromotionSpace>
    @RequestMapping("saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){


        if (promotionSpace.getId()==null) {
            promotionSpaceService.savePromotionSpace(promotionSpace);

            return new ResponseResult(true, 200, "保存成功", null);
        }else {

            promotionSpaceService.updatePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"更改成功",null);

        }

    }

    //<http://localhost:8080/ssm-web/PromotionSpace/findPromotionSpaceById>
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){

        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true,200,"回显成功",promotionSpaceById);

    }


}
