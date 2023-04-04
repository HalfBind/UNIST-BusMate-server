package cse364.project.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private Integer Id;

    private String gender;
    private Integer age;
    private String occupation;
    private String zipCode;

}
