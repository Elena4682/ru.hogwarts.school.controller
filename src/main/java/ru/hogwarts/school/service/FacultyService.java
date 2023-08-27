package ru.hogwarts.school.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Method add was invoked");
        return repository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Method find was invoked");
        return repository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Method edit was invoked");
        return repository.findById(faculty.getId())
                .map(dbEntity ->{
                    dbEntity.setName(faculty.getName());
                    dbEntity.setColor(faculty.getColor());
                    repository.save(dbEntity);
                    return dbEntity;})
                .orElse(null);
    }

    public boolean deleteFaculty(long id) {
        logger.info("Method delete was invoked");
        return repository.findById(id)
                .map(entity ->{
                    repository.delete(entity);
                    return true;
                })
                .orElse(false);
    }
    public Collection<Faculty> findByColor (String name,String color){
        return repository.findByColorOrNameIgnoreCase(name,color);
    }

    public Faculty get(long facultyId) {
        return null;
    }
    public String longestName(){
        return repository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }
    public void returnInteger(){
        System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
    }
}
