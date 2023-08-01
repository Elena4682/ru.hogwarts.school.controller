package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public Student findStudent(long id) {
        return repository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return repository.findById(student.getId())
                .map(dbEntity ->{
                    dbEntity.setName(student.getName());
                    dbEntity.setAge(student.getAge());
                    repository.save(dbEntity);
                    return dbEntity;})
                .orElse(null);
    }

    public boolean deleteStudent(long id) {
        return repository.findById(id)
                .map(entity ->{
                    repository.delete(entity);
                    return true;
                })
                .orElse(false);

    }
    public Collection<Student> findByAge (int minAge, int maxAge){
        return repository.findByAgeBetween(minAge,maxAge);
    }
    public Collection<Student> findStudentsByFaculty(long facultyId){
        return repository.findByFaculty_Id(facultyId);
    }
}
