package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseSectionMapper {

    //根据章节id联表查询章节和课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);


    //根据课程id查找
    public Course findCourseById(Integer courseId);


    //新增章节信息
    public void  saveCourseSection(CourseSection courseSection);


    //修改章节信息
    public void  updateCourseSection(CourseSection courseSection);

    //根据章节id更改章节状态
    public void updateCourseSectionStatus(CourseSection courseSection);

}
