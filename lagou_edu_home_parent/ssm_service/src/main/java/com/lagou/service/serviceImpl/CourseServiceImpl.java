package com.lagou.service.serviceImpl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {


        return courseMapper.findCourseByCondition(courseVo);


    }

    @Override
    public void saveCourseAndTeacher(CourseVo courseVo) {


        try {
            Course course = new Course();

            //自动匹配字段，将courseVo中的字段自动封装到course中
            BeanUtils.copyProperties(course,courseVo);


            //手动添加时间
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            courseMapper.saveCourse(course);

            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher,courseVo);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(course.getId());

            courseMapper.saveTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseVo findCourseAndTeacherById(Integer id) {

        CourseVo courseAndTeacherById = courseMapper.findCourseAndTeacherById(id);
        return courseAndTeacherById;
    }

    @Override
    public void updateCourseAndTeacherById(CourseVo courseVo) {

        try {
            Date date = new Date();
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVo);
            course.setUpdateTime(date);
            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);
            teacher.setUpdateTime(date);

            //teacher.setCourseId(courseVo.getId());

            courseMapper.updateTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Course> test(Course course) {
        return courseMapper.test(course);
    }

    //通过id更新course状态
    @Override
    public void updateStatus(Course course) {

        courseMapper.updateStatus(course);
    }


}
