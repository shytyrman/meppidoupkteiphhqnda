package com.example.meppidoupkteiphhqnda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_seq"
    )
    @SequenceGenerator(
            name = "person_seq",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    private Long id;

    private String fullName;
    private LocalDate birthday;
    private String phoneNumber;
    private String phoneNumberAdditional;
    private LocalDateTime createdAt;

    public Person(String fullName, LocalDate birthday, String phoneNumber, String phoneNumberAdditional) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.phoneNumberAdditional = phoneNumberAdditional;
        this.createdAt = LocalDateTime.now();
    }

    public Person() {
        this.createdAt = LocalDateTime.now();
    }
}
