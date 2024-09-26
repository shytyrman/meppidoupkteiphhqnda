package com.example.meppidoupkteiphhqnda;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import com.example.meppidoupkteiphhqnda.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@EnableMongoRepositories
public class MeppidoupkteiphhqndaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeppidoupkteiphhqndaApplication.class, args);
    }

}
