package cse364.milestone1application.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
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
}
