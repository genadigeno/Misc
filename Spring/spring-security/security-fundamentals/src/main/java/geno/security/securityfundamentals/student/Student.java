package geno.security.securityfundamentals.student;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
    public Student() {}

    public Student(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    private Integer id;
    private String name;
    private String lastName;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
