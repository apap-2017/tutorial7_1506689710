package com.example.dao;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper
{
    @Select("SELECT id_course, name, credits FROM COURSE WHERE id_course = #{id_course}")
    @Results(value = {
            @Result(property = "id_course", column = "id_course"),
            @Result(property = "name", column = "name"),
            @Result(property = "credits", column = "credits"),
            @Result(property = "students", column = "id_course",
                    javaType = List.class,
                    many = @Many(select = "selectStudents"))
    })
    CourseModel selectCourse(@Param("id_course") String id_course);

    @Select("SELECT STUDENT.npm, STUDENT.name " +
            "FROM STUDENT JOIN STUDENTCOURSE " +
            "ON STUDENT.npm = STUDENTCOURSE.npm " +
            "WHERE STUDENTCOURSE.id_course = #{id_course}")
    List<StudentModel> selectStudents(@Param("id_course") String id_course);

}