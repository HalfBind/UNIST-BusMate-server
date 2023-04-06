package cse364.milestone1application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document
@AllArgsConstructor
public class Employee {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String role;

    public static Long idCounter = 0L;

    public Employee() {
        this.id = ++idCounter;
    }
}
