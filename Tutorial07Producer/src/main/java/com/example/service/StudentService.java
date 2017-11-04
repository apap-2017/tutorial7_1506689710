package com.example.service;

import com.example.model.StudentModel;

import java.util.List;

public interface StudentService
{
    StudentModel selectStudent (String npm);

    List<StudentModel> selectAllStudents ();

    void addStudent (StudentModel student);

    void deleteStudent(String npm);

    void updateStudent(StudentModel student);
}
