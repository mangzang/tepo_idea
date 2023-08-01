package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAllCourse")
    public ResponseResult findAllCourse(@RequestBody CourseVo courseVo) {

        List<Course> courseList = courseService.findCourseByCondition(courseVo);

        ResponseResult responseResult = new ResponseResult(true, 0, "响应成功", courseList);

        return responseResult;


    }

    //**接口地址**: http://localhost:8080/ssm-web/course/courseUpload
//即将存放地址: http://localhost:8080/upload/
    //file=1597112871741.JPG
    @RequestMapping("/courseUpload")
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


    //保存课程course和教师teacher信息
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) {

        if (courseVo.getId() == null) {
            courseService.saveCourseAndTeacher(courseVo);

            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

            return responseResult;

        } else {

            courseService.updateCourseAndTeacherById(courseVo);

            return new ResponseResult(true, 200, "更改成功", null);

        }


    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {  //get request

        CourseVo courseVo = courseService.findCourseAndTeacherById(id);

        System.out.println(courseVo);
        return new ResponseResult(true, 200, "回显成功", courseVo);


    }


    @RequestMapping("/test")
    public void test() {

        Course course = new Course();
        course.setId(null);
        List<Course> courseList = courseService.test(course);
        System.out.println(courseList);
    }

    //通过id更新course状态

    @RequestMapping("/updateCourse")
    public ResponseResult updateCourse(int status, Integer id) {

        Course course = new Course();
        course.setId(id);
        course.setUpdateTime(new Date());
        course.setStatus(status);
        courseService.updateStatus(course);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "更改状态成功", map);

    }


}