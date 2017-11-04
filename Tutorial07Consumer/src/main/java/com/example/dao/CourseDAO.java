package com.example.dao;

import com.example.model.CourseModel;

import java.util.List;

public interface CourseDAO
{
    CourseModel selectCourse(String id_course);
    List<CourseModel> selectAllCourse();
}
