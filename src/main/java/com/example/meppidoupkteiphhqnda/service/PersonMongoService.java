package com.example.meppidoupkteiphhqnda.service;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.repository.PersonMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
@AllArgsConstructor
public class PersonMongoService {

    private PersonMongoRepository personMongoRepository;

    public void make() {
        PersonMongo person = new PersonMongo();
        person.setFullName("Just a Random name");
        person.setPhoneNumber("7773334455");
        person.setPhoneNumberAdditional("8886665544");
        person.setBirthday(LocalDate.of(2002, Month.APRIL, 23));

        personMongoRepository.save(person);
    }
}
