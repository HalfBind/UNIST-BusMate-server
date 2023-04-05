package cse364.milestone1application.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Document
@AllArgsConstructor
@Data
public class User {
    @Getter
    private Long id;

    @Getter
    private String gender;
    @Getter
    private Long age;
    @Getter
    private Long occupation;
    @Getter
    private String zipCode;

    public static Long idCounter = 0L;

    public User() {
        this.id = ++idCounter;
    }
}
