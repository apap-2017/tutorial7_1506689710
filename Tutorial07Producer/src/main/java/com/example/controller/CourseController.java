package com.example.controller;

import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController
{
    @Autowired
    CourseService courseDAO;

    @RequestMapping("/course/view/{id_course}")
    public String view(Model model, @PathVariable(value = "id_course") String id_course)
    {
        model.addAttribute("course", courseDAO.selectCourse(id_course));
        return "view-course";
    }
}
