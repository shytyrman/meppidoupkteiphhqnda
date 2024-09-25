package com.example.meppidoupkteiphhqnda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private LocalDateTime birthday;
    private String phoneNumber;
    private String phoneNumberAdditional;
    private LocalDateTime createdAt;

    public Person(String fullName, LocalDateTime birthday, String phoneNumber, String phoneNumberAdditional) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.phoneNumberAdditional = phoneNumberAdditional;
        this.createdAt = LocalDateTime.now();
    }
}
