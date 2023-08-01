package com.lagou.service.serviceImpl;

import com.lagou.dao.CourseSectionMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseSectionServiceImpl implements CourseSectionService {

    @Autowired
    private CourseSectionMapper courseSectionMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id) {

        return  courseSectionMapper.findSectionAndLessonByCourseId(id);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {

       return courseSectionMapper.findCourseById(courseId);



    }

    @Override
    public void saveSection(CourseSection courseSection) {

        Date date = new Date();

        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseSectionMapper.saveCourseSection(courseSection);

    }

    @Override
    public void updateSection(CourseSection courseSection) {


        courseSection.setUpdateTime(new Date());
        courseSectionMapper.updateCourseSection(courseSection);


    }

    @Override
    public void updateSectionStatus(int id, int status) {

        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());

        courseSectionMapper.updateCourseSectionStatus(courseSection);

    }
}
