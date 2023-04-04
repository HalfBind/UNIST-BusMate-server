package cse364.project.domain;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class User {
    @Id
    private Integer Id;

    private String gender;
    private Integer age;
    private String occupation;
    private String zipCode;

}
