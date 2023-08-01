package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService facultyService;
    @BeforeEach
    public void beforeEach(){
        facultyService = new FacultyService(facultyRepository);
    }

    public void FacultyTest(){
        Faculty expected = new Faculty(1L,"Name","Color");
        Mockito.when(facultyRepository.save(new Faculty(1L,"Name","Color")))
                .thenReturn(new Faculty(1L,"Name","Color"));
        Faculty actual = facultyService.addFaculty(new Faculty(1L,"Name","Color"));
        assertEquals(expected,actual);
    }
}
