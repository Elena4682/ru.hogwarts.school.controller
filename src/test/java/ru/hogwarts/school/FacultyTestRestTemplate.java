package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyTestRestTemplate {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testDefaultMessage() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }
    @Test
    public void testGetFaculty()throws  Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port , String.class))
                .isNotEmpty();
    }
    @Test
    public void testPostFaculty()throws  Exception{
        Faculty faculty = new Faculty();
        faculty.setName("Alex");
        faculty.setColor("green");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port
                        + "/faculty", faculty, String.class));
    }
    @Test
    public void testPutFaculty()throws  Exception {
        Faculty faculty = new Faculty();
        HttpEntity<Faculty> httpFaculty = new HttpEntity<>(faculty);
        faculty.setName("Alex");
        faculty.setColor("green");
        ResponseEntity<Faculty> facultyEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.PUT,
                httpFaculty,
                Faculty.class);
        Assertions
                .assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
