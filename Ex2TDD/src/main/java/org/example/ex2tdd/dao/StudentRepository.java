package org.example.ex2tdd.dao;

import org.example.ex2tdd.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//lie au service
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByFirstnameAndLastname(String firstname, String lastname);

    List<Student> findAllByLastname(String search);

    List<Student> findAllByLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(String firstname, String lastname);
}
