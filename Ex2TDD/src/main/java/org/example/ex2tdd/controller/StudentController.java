package org.example.ex2tdd.controller;


import jakarta.validation.Valid;
import org.example.ex2tdd.model.Student;
import org.example.ex2tdd.service.AuthService;
import org.example.ex2tdd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class StudentController {
    private final StudentService studentService;
    private String location = "src/main/resources/static";


    //ajouter return page login
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/formulaire")
    public String formAddStudent(Model model){
        model.addAttribute("student", new Student());
        return "form";
    }

    //ici
    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {return "form";}
        else {
            studentService.createStudent(student);
            return "redirect:/students";
        }
    }

    @RequestMapping("/students") // /students?search=Toto
    public String showStudents(@RequestParam(name = "search", required = false) String search, Model model){
        if (search == null) {
            model.addAttribute("students", studentService.getAllStudents());
        } else {
            model.addAttribute("students", studentService.searchStudents(search));
        }
        return "list";
    }

    @RequestMapping("/student/{id}")
    public String showStudent(@PathVariable("id") Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "detail";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("studentId") Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @RequestMapping("/update")
    public String formUpdate(@RequestParam("studentId") Long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "form";
    }
}