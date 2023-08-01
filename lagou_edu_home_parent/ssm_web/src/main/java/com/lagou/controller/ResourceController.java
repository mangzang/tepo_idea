package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

//URL: http://localhost:8080/ssm-web/resource/findAllResource
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){

        PageInfo pageInfo = resourceService.findResourceByCondition(resourceVo);

        return new ResponseResult(true,200,"分页查询成功",pageInfo);


    }

    //查询所有资源
    //URL: http://localhost:8080/ssm-web/ResourceCategory/findAllResourceCategory
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<Resource> list = resourceService.findAllResource();

        return new ResponseResult(true,200,"查询全部资源成功",list);

    }

    //URL: http://localhost:8080/ssm-web/resource/saveOrUpdateResource
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        if (resource.getId()==null){
            //新建资源
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"新建资源成功",null);
        }else {
            //更改资源
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"更改资源成功",null);
        }
    }


    //根据id删除资源
    //URL: http://localhost:8080/ssm-web/resource/deleteResource?id=5
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源成功，根据id",null);
    }









}//
