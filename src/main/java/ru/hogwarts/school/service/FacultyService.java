package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return repository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(dbEntity ->{
                    dbEntity.setName(faculty.getName());
                    dbEntity.setColor(faculty.getColor());
                    repository.save(dbEntity);
                    return dbEntity;})
                .orElse(null);
    }

    public boolean deleteFaculty(long id) {
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

}
