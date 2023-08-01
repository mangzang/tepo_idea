package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseSectionService {




    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);


    public Course findCourseByCourseId(Integer courseId);


    //新增章节信息
    public void saveSection(CourseSection courseSection);

    //更改章节信息
    public void updateSection(CourseSection courseSection);


    //更改章节状态
    public void updateSectionStatus(int id,int status);

}
