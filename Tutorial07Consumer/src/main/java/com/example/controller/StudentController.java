package com.example.controller;

import com.example.model.StudentModel;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
        model.addAttribute("page", "home");
        model.addAttribute("page_title", "Home");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
        model.addAttribute("page", "addstudent");
        model.addAttribute("page_title", "Menambah Siswa");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa,
            Model model)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);
        model.addAttribute("page", "addstudent");
        model.addAttribute("page_title", "Menambah Siswa - Berhasil");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        model.addAttribute("page", "home");
        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("page_title", npm + " - " + student.getName());
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("page_title", "Siswa dengan NPM " + npm + " Tidak Ditemukan");
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
            model.addAttribute("page_title", npm + " - " + student.getName());
            return "view";
        } else {
            model.addAttribute("page_title", "Siswa dengan NPM " + npm + " Tidak Ditemukan");
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute("page", "viewall");
        model.addAttribute ("students", students);
        model.addAttribute("page_title", "Data Seluruh Siswa");
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (@PathVariable(value = "npm") String npm,
                          Model model)
    {
        if (studentDAO.selectStudent(npm) != null)
        {
            studentDAO.deleteStudent (npm);
            model.addAttribute("page_title", "Menghapus Siswa NPM " + npm);
            return "delete";
        }
        model.addAttribute("page_title", "Siswa dengan NPM " + npm + " Tidak Ditemukan");
        return "not-found";
    }

    @RequestMapping("/student/update/{npm}")
    public String update(Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null)
        {
            model.addAttribute("student", student);
            model.addAttribute("page_title", "Update Siswa dengan NPM " + npm);
            return "form-update";
        }
        model.addAttribute("page_title", "Siswa dengan NPM " + npm + " Tidak Ditemukan");
        return "not-found";
    }

    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit(Model model, @Valid StudentModel student, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("student", studentDAO.selectStudent(student.getNpm()));
            model.addAttribute("page_title", "Update Siswa dengan NPM " + student.getNpm());
            return "form-update";
        }
        studentDAO.updateStudent(student);
        model.addAttribute("page_title", "Update Berhasil - " + student.getNpm());
        return "success-update";
    }

}
