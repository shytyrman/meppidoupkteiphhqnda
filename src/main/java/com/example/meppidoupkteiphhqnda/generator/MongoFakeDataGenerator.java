package com.example.meppidoupkteiphhqnda.generator;

import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.repository.PersonMongoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoFakeDataGenerator implements fakeDataGenerator, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PersonMongoRepository repository;

    Faker faker = new Faker();

    @Override
    public void generate(Integer count) {

        for (int i = 0; i < count; i++) {
            PersonMongo personMongo = new PersonMongo();
            personMongo.setFullName(faker.name().fullName());
            personMongo.setBirthday(faker.timeAndDate().birthday());
            personMongo.setPhoneNumber(faker.phoneNumber().phoneNumber());
            personMongo.setPhoneNumberAdditional(faker.phoneNumber().cellPhoneInternational());
            repository.save(personMongo);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        repository.deleteAll();
        generate(101);
    }
}
