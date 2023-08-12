package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int minAge, int maxAge);
    Collection<Student>findByFaculty_Id(long facultyId);
    @Query(value = "select COUNT(*) from student", nativeQuery = true)
    int getStudentCount();
    @Query(value = "select AVG(age) from student", nativeQuery = true)
    int getAverageAge();
    @Query(value = "select from student order by id desc limit 5", nativeQuery = true)
    List<Student> getListStudents();

}
