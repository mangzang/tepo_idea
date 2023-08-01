package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {


    public List<Course> findCourseByCondition(CourseVo courseVo);

    //插入course
    public void saveCourse(Course course);

    //插入teacher
    public void saveTeacher(Teacher teacher);

    //更新course
    public void updateCourse(Course course);

    //更新teacher
    public void updateTeacher(Teacher teacher);


/*    //根据id查询course
    public Course findCourseById(Integer id);

    //根据id查询teacher
    public Teacher findTeacherById(Integer courseId);*/

    //通过id查询course 和teacher
    public CourseVo findCourseAndTeacherById(Integer id);


    //修改课程状态
    public void updateStatus(Course course);



    //test
    public List<Course> test(Course course);





}
