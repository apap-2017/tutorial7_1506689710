package com.example.service;

import com.example.dao.StudentDAO;
import com.example.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class StudentServiceRest implements StudentService
{
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public StudentModel selectStudent(String npm) {
        return studentDAO.selectStudent(npm);
    }

    @Override
    public List<StudentModel> selectAllStudents() {
        return studentDAO.selectAllStudents();
    }

    @Override
    public void addStudent(StudentModel student) {

    }

    @Override
    public void deleteStudent(String npm) {

    }

    @Override
    public void updateStudent(StudentModel student) {

    }

    @Override
    public void addCourse(String npm, String idCourse) {

    }
}
