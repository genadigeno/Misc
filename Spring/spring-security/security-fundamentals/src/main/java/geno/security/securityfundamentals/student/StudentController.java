package geno.security.securityfundamentals.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James", "Bond"),
        new Student(2, "Vinme", "Bond"),
        new Student(3, "Freak", "Bond")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) throws Throwable {
        return STUDENTS.stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return studentId.equals(student.getId());
            }
        }).findFirst().orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new IllegalStateException("Student with id: "+studentId+" not found");
            }
        });
    }
}
