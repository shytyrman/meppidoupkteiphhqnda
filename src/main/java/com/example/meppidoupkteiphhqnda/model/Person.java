package com.example.meppidoupkteiphhqnda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
}
