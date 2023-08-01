package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

//**接口地址**: http://localhost:8080/ssm-web/PromotionAd/findAllPromotionAdByPage?currentPage=1&pageSize=5
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage( PromotionAdVO promotionAdVO){

        PageInfo pageInfo = promotionAdService.findPromotionAd(promotionAdVO);

        return new ResponseResult(true,200,"分页显示",pageInfo);

    }


    //文件上传**接口地址**: <http://localhost:8080/ssm-web/PromotionAd/PromotionAdUpload>
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        String originalFilename = file.getOriginalFilename();
        //创建新文件名，避免前端传来的文件名重复
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.indexOf("."));

        ////2.获取项目部署路径//// D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");

        String Path = realPath.substring(0, realPath.lastIndexOf("ssm-web"));

        String filePath = Path + "upload//";


        File newFile = new File(filePath, newFileName);

        if (!newFile.getParentFile().exists()) {

            newFile.getParentFile().mkdirs();
        }

        //存到服务器的webapps/upload目录下
        file.transferTo(newFile);

        Map<String, String> map = new HashMap<>();
        //"fileName": "1597112871741.JPG",
        //  "filePath": "http://localhost:8080/upload/1597112871741.JPG"

        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);


        //返回json串，进行回显
        ResponseResult responseResult = new ResponseResult(true, 200, "上传成功了", map);

        return responseResult;


    }

//**接口地址**: <http://localhost:8080/ssm-web/PromotionAd/saveOrUpdatePromotionAd>
    //新增或者更改promotionAd
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId()==null) {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"更改广告成功",null);

        }
    }

    //http://localhost:8080/ssm-web/PromotionAd/findPromotionAdById?id=1091
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){

        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"查询成功",promotionAdById);

    }


    //**接口地址**: <http://localhost:8080/ssm-web/PromotionAd/updatePromotionAdStatus?id=1074&status=1
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd){

        promotionAdService.updatePromotionAdStatus(promotionAd);

        return new ResponseResult(true,200,"更改状态成功",promotionAd);

    }
}
