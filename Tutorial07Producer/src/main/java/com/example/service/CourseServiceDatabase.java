package com.example.service;

import com.example.dao.CourseMapper;
import com.example.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceDatabase implements CourseService
{
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseModel selectCourse(String id_course)
    {
        return courseMapper.selectCourse(id_course);
    }

    @Override
    public List<CourseModel> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }
}
