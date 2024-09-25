package com.example.meppidoupkteiphhqnda.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "person")
@Data
@NoArgsConstructor
public class PersonMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private LocalDate birthday;
    private String phoneNumber;
    private String phoneNumberAdditional;
    private LocalDateTime createdAt;

    public PersonMongo(String fullName, LocalDate birthday, String phoneNumber, String phoneNumberAdditional) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.phoneNumberAdditional = phoneNumberAdditional;
        this.createdAt = LocalDateTime.now();
    }
}
