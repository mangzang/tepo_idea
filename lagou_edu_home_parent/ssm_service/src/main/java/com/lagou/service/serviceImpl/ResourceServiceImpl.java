package com.lagou.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo findResourceByCondition(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> resourceList = resourceMapper.findResourceByCondition(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;


    }

    //查询所有资源
    @Override
    public List<Resource> findAllResource() {

        List<Resource> list = resourceMapper.findAllResource();
        return list;
    }

    @Override  //新建资源
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);

    }

    @Override    //更改资源
    public void updateResource(Resource resource) {

        resource.setUpdatedTime(new Date());
        resourceMapper.updateResource(resource);
    }

    //根据id删除资源
    @Override
    public void deleteResource(Integer id) {


        resourceMapper.deleteResource(id);
    }
}
