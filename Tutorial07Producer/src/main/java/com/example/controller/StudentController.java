package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.model.StudentModel;
import com.example.service.StudentService;

import javax.validation.Valid;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (@PathVariable(value = "npm") String npm)
    {
        if (studentDAO.selectStudent(npm) != null)
        {
            studentDAO.deleteStudent (npm);
            return "delete";
        }
        return "not-found";
    }

    @RequestMapping("/student/update/{npm}")
    public String update(Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null)
        {
            model.addAttribute("student", student);
            return "form-update";
        }
        return "not-found";
    }

    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit(Model model, @Valid StudentModel student, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("student", studentDAO.selectStudent(student.getNpm()));
            return "form-update";
        }
        studentDAO.updateStudent(student);
        return "success-update";
    }

}
