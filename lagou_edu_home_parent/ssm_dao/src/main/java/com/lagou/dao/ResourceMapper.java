package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    //分页查询资源
    public List<Resource> findResourceByCondition(ResourceVo resourceVo);

    //查询所有资源
    public List<Resource> findAllResource();


    //新建资源
    public void saveResource(Resource resource);

    //更改资源
    public void updateResource(Resource resource);


    //根据id删除资源
    public void deleteResource(Integer id);

}
