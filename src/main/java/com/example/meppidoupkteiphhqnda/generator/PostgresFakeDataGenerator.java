package com.example.meppidoupkteiphhqnda.generator;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PostgresFakeDataGenerator implements fakeDataGenerator, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PersonRepository repository;

    Faker faker = new Faker();

    @Override
    public void generate(Integer count) {

        for (int i = 0; i < count; i++) {
            Person person = new Person();
            person.setFullName(faker.name().fullName());
            person.setBirthday(faker.timeAndDate().birthday());
            person.setPhoneNumber(faker.phoneNumber().phoneNumber());
            person.setPhoneNumberAdditional(faker.phoneNumber().cellPhoneInternational());
            repository.save(person);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        repository.deleteAll();
        generate(101);
    }
}
