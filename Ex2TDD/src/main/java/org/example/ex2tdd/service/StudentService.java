package org.example.ex2tdd.service;
import org.example.ex2tdd.dao.StudentRepository;
import org.example.ex2tdd.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
//    private List<Student> students = new ArrayList<>();
//    private Long currentId = 1L;


//    public StudentService(){
//        Student student = new Student(currentId++, "Toto", "Doe", 25, "toto@email.fr");
//        students.add(student);
//    }
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchStudents(String search){
        return studentRepository.findAllByLastname(search);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updateStudent){
        Student studentExist = getStudentById(id);
        if(studentExist != null){
            studentRepository.save(updateStudent);
        }
        return studentExist;
    }
}


