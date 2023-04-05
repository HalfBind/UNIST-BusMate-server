package cse364.milestone1application.domain;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
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
