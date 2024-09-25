package com.example.meppidoupkteiphhqnda.service;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public void make() {
        Person person = new Person();
        person.setFullName("Just a Random name");
        person.setPhoneNumber("7773334455");
        person.setPhoneNumberAdditional("8886665544");
        person.setBirthday(LocalDate.of(2002, Month.APRIL, 23));

        personRepository.save(person);
    }
}
