package ru.hogwarts.school.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.logging.Logger;

@Service
public class StudentService {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {

        this.repository = repository;
    }

    public Student addStudent(Student student) {
        logger.info("Method add was invoked");
        return repository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Method find was invoked");
        return repository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Method edit was invoked");
        return repository.findById(student.getId())
                .map(dbEntity ->{
                    dbEntity.setName(student.getName());
                    dbEntity.setAge(student.getAge());
                    repository.save(dbEntity);
                    return dbEntity;})
                .orElse(null);
    }

    public boolean deleteStudent(long id) {
        logger.info("Method delete was invoked");
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
    public int getStudentCount(){
        return repository.getStudentCount();
    }
    public int getAverageAge(){
        return repository.getAverageAge();
    }
    public List<Student> getListStudents(){
        return repository.getListStudents();
    }
}
