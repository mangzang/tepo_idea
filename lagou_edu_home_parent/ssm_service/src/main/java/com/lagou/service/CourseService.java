package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.util.List;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVo courseVo);

    //插入course和teacher信息
    public void saveCourseAndTeacher(CourseVo courseVo);

    //通过id查询course和teacher
    public CourseVo findCourseAndTeacherById(Integer id);


    //通过id更改course and teacher
    public void updateCourseAndTeacherById(CourseVo courseVo);

    //test
    public List<Course> test(Course course);

    //通过id更新course状态
    public void updateStatus(Course course);


}
