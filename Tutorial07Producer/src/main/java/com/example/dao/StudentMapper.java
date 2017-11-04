package com.example.dao;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper
{


    @Select("select npm, name, gpa from student where npm = #{npm}")
    @Results(value = {
            @Result(property = "npm", column = "npm"),
            @Result(property = "name", column = "name"),
            @Result(property = "gpa", column = "gpa"),
            @Result(property = "courses", column = "npm",
            javaType = List.class,
            many = @Many(select = "selectCourses"))
    })
    StudentModel selectStudent(@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    @Results(value = {
            @Result(property = "npm", column = "npm"),
            @Result(property = "name", column = "name"),
            @Result(property = "gpa", column = "gpa"),
            @Result(property = "courses", column = "npm",
                    javaType = List.class,
                    many = @Many(select = "selectCourses"))
    })
    List<StudentModel> selectAllStudents();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent(StudentModel student);

    @Delete("DELETE FROM STUDENT WHERE npm = #{npm}")
    void deleteStudent(@Param("npm") String npm);

    @Update("UPDATE STUDENT " +
            "SET name = #{name}, gpa = #{gpa} " +
            "WHERE npm = #{npm};")
    void updateStudent(StudentModel student);

    @Select("SELECT COURSE.id_course, name, credits " +
            "FROM STUDENTCOURSE JOIN COURSE " +
            "ON STUDENTCOURSE.id_course = COURSE.id_course " +
            "WHERE STUDENTCOURSE.npm = #{npm}")
    List<CourseModel> selectCourses(@Param("npm") String npm);
}
