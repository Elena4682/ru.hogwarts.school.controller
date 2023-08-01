package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    @BeforeEach
    public void beforeEach(){
        studentService = new StudentService(studentRepository);
    }

    public void StudentTest(){
        Student expected = new Student(1L,"Name",18);
        Mockito.when(studentRepository.save(new Student(1L,"Name",18)))
                .thenReturn(new Student(1L,"Name",18));
        Student actual = studentService.addStudent(new Student(1L,"Name",18));
        assertEquals(expected,actual);
    }
}
