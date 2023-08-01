package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseSectionController {

    @Autowired
    private CourseSectionService courseSectionService;


    ////http://localhost:8080/ssm-web/courseContent/findSectionAndLesson?courseId=7
    //    public ResponseResult
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){

        List<CourseSection> list = courseSectionService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true,200,"查询成功",list);
    }

    //http://localhost:8080/ssm-web/courseContent/findCourseByCourseId?courseId=16
    //更加courseId查询course
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseSectionService.findCourseByCourseId(courseId);

        return new ResponseResult(true,200,"回显成功",course);

    }


    //URL: http://localhost:8080/ssm-web/courseContent/saveOrUpdateSection
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){


        if (courseSection.getId()==null) {
            courseSectionService.saveSection(courseSection);

            return new ResponseResult(true, 200, "新增章节成功", null);
        }else {

            courseSectionService.updateSection(courseSection);
            return new ResponseResult(true,200,"更改章节成功",null);

        }
    }


    //http://localhost:8080/ssm-web/courseContent/updateSectionStatus?id=7&status=1
    @RequestMapping("updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseSectionService.updateSectionStatus(id,status);

        return new ResponseResult(true,200,"修改章节状态成功",null);

    }


}
