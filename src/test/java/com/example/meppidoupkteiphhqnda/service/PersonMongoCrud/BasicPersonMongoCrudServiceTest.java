package com.example.meppidoupkteiphhqnda.service.PersonMongoCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.Person.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.Person.UpdatePersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.PersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.UpdatePersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonMongoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;
@ActiveProfiles("test")
@SpringBootTest
public class BasicPersonMongoCrudServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PersonMongoCrudService service;

    @Autowired
    PersonMongoRepository repository;

    Faker faker = new Faker();

    @Test
    public void searchById() {

        List<PersonMongo> persons = repository.findAll();

        PersonMongo person = persons.iterator().next();
        PersonMongoByDatas datas = new PersonMongoByDatas(person.getId(), null);

        PersonMongo personFromService = service.find(datas);
        PersonMongo personFromRepository = repository.findById(person.getId()).get();

        System.out.println(personFromService);
        System.out.println(personFromRepository);

        assertEquals(personFromService, personFromRepository);
    }

    @Test
    public void searchByPhoneNumber() {

        List<PersonMongo> persons = repository.findAll();

        PersonMongo person = persons.iterator().next();
        PersonMongoByDatas datasForId = new PersonMongoByDatas(person.getId(), null);

        PersonMongo personFromServiceById = service.find(datasForId);

        PersonMongoByDatas datasForPhoneNumber = new PersonMongoByDatas(null, personFromServiceById.getPhoneNumber());
        PersonMongo personFromServiceByPhoneNumber = service.find(datasForPhoneNumber);

        System.out.println(personFromServiceById);
        System.out.println(personFromServiceByPhoneNumber);

        assertEquals(personFromServiceById, personFromServiceByPhoneNumber);
    }

    @Test
    public void testFindAll() {

        List<PersonMongo> personsBefore = repository.findAll();

        Integer limit = faker.random().nextInt(0, 100);
        Integer offset = faker.random().nextInt(0, 100);

        Filter filter = new Filter(limit, offset);

        List<PersonMongo> persons = service.findAll(filter);

        assertTrue(persons.size() <= limit && persons.get(0).equals(personsBefore.get(offset)));

    }

    @Test
    public void testUpdateSomeFieldsById() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        String changeFullName = null;
        LocalDate changeBirthday = null;
        String changePhoneNumber = null;
        String changeAdditionalPhoneNumber = null;


        if (faker.random().nextBoolean()) {
            changeFullName = faker.funnyName().name();
            System.out.println("Full Name set!");
        }
        if (faker.random().nextBoolean()) {
            changeBirthday = faker.timeAndDate().birthday();
            System.out.println("Birthday set!");
        }
        if (faker.random().nextBoolean()) {
            changePhoneNumber = faker.phoneNumber().phoneNumber();
            System.out.println("Phone Number set!");
        }
        if (faker.random().nextBoolean()) {
            changeAdditionalPhoneNumber = faker.phoneNumber().cellPhoneInternational();
            System.out.println("Additional Phone Number set!");
        }

        System.out.println(chosenPerson);

        UpdatePersonMongoByDatas updateDatasForId = new UpdatePersonMongoByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, chosenPersonId, null);

        service.update(updateDatasForId);

        PersonMongo person = repository.findById(chosenPersonId).get();

        boolean nameCondition;
        boolean birthdayCondition;
        boolean phoneCondition;
        boolean phoneAdditionalCondition;

        if (changeFullName != null) {
            nameCondition = changeFullName.equals(person.getFullName());
        }
        else nameCondition = true;

        if (changeBirthday != null) {
            birthdayCondition = changeBirthday.equals(person.getBirthday());
        }
        else birthdayCondition = true;

        if (changePhoneNumber != null) {
            phoneCondition = changePhoneNumber.equals(person.getPhoneNumber());
        }
        else phoneCondition = true;

        if (changeAdditionalPhoneNumber != null) {
            phoneAdditionalCondition = changeAdditionalPhoneNumber.equals(person.getPhoneNumberAdditional());
        }
        else phoneAdditionalCondition = true;

        assertTrue(
                nameCondition &&
                        birthdayCondition &&
                        phoneCondition &&
                        phoneAdditionalCondition
        );
    }

    @Test
    public void testUpdateAllFieldsById() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        String changeFullName = null;
        LocalDate changeBirthday = null;
        String changePhoneNumber = null;
        String changeAdditionalPhoneNumber = null;

        changeFullName = faker.funnyName().name();
        System.out.println("Full Name set!");
        changeBirthday = faker.timeAndDate().birthday();
        System.out.println("Birthday set!");
        changePhoneNumber = faker.phoneNumber().phoneNumber();
        System.out.println("Phone Number set!");
        changeAdditionalPhoneNumber = faker.phoneNumber().cellPhoneInternational();
        System.out.println("Additional Phone Number set!");

        System.out.println(repository.findById(chosenPersonId));

        UpdatePersonMongoByDatas updateDatasForId = new UpdatePersonMongoByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, chosenPersonId, null);

        service.update(updateDatasForId);

        PersonMongo person = repository.findById(chosenPersonId).get();

        assertTrue(
                changeFullName.equals(person.getFullName()) &&
                        changeBirthday.equals(person.getBirthday()) &&
                        changePhoneNumber.equals(person.getPhoneNumber()) &&
                        changeAdditionalPhoneNumber.equals(person.getPhoneNumberAdditional())
        );
    }

    @Test
    public void testUpdateSomeFieldsPhoneNumber() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        String changeFullName = null;
        LocalDate changeBirthday = null;
        String changePhoneNumber = null;
        String changeAdditionalPhoneNumber = null;


        if (faker.random().nextBoolean()) {
            changeFullName = faker.funnyName().name();
            System.out.println("Full Name set!");
        }
        if (faker.random().nextBoolean()) {
            changeBirthday = faker.timeAndDate().birthday();
            System.out.println("Birthday set!");
        }
        if (faker.random().nextBoolean()) {
            changePhoneNumber = faker.phoneNumber().phoneNumber();
            System.out.println("Phone Number set!");
        }
        if (faker.random().nextBoolean()) {
            changeAdditionalPhoneNumber = faker.phoneNumber().cellPhoneInternational();
            System.out.println("Additional Phone Number set!");
        }

        System.out.println(repository.findById(chosenPersonId));

        PersonMongo personBefore = repository.findById(chosenPersonId).get();

        UpdatePersonMongoByDatas updateDatasForPhoneNumber = new UpdatePersonMongoByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, null, personBefore.getPhoneNumber());

        service.update(updateDatasForPhoneNumber);

        PersonMongo person = repository.findById(chosenPersonId).get();


        boolean nameCondition;
        boolean birthdayCondition;
        boolean phoneCondition;
        boolean phoneAdditionalCondition;

        if (changeFullName != null) {
            nameCondition = changeFullName.equals(person.getFullName());
        }
        else nameCondition = true;

        if (changeBirthday != null) {
            birthdayCondition = changeBirthday.equals(person.getBirthday());
        }
        else birthdayCondition = true;

        if (changePhoneNumber != null) {
            phoneCondition = changePhoneNumber.equals(person.getPhoneNumber());
        }
        else phoneCondition = true;

        if (changeAdditionalPhoneNumber != null) {
            phoneAdditionalCondition = changeAdditionalPhoneNumber.equals(person.getPhoneNumberAdditional());
        }
        else phoneAdditionalCondition = true;

        assertTrue(
                nameCondition &&
                        birthdayCondition &&
                        phoneCondition &&
                        phoneAdditionalCondition
        );
    }

    @Test
    public void testUpdateAllFieldsByPhoneNumber() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        String changeFullName = null;
        LocalDate changeBirthday = null;
        String changePhoneNumber = null;
        String changeAdditionalPhoneNumber = null;

        changeFullName = faker.funnyName().name();
        System.out.println("Full Name set!");
        changeBirthday = faker.timeAndDate().birthday();
        System.out.println("Birthday set!");
        changePhoneNumber = faker.phoneNumber().phoneNumber();
        System.out.println("Phone Number set!");
        changeAdditionalPhoneNumber = faker.phoneNumber().cellPhoneInternational();
        System.out.println("Additional Phone Number set!");

        System.out.println(repository.findById(chosenPersonId));

        PersonMongo personBefore = repository.findById(chosenPersonId).get();

        UpdatePersonMongoByDatas updateDatasForPhoneNumber = new UpdatePersonMongoByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, null, personBefore.getPhoneNumber());

        service.update(updateDatasForPhoneNumber);

        PersonMongo person = repository.findById(chosenPersonId).get();

        boolean nameCondition;
        boolean birthdayCondition;
        boolean phoneCondition;
        boolean phoneAdditionalCondition;

        assertTrue(
                changeFullName.equals(person.getFullName()) &&
                        changeBirthday.equals(person.getBirthday()) &&
                        changePhoneNumber.equals(person.getPhoneNumber()) &&
                        changeAdditionalPhoneNumber.equals(person.getPhoneNumberAdditional())
        );
    }

    @Test(priority = 1)
    public void testDeleteById() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        PersonMongoByDatas datasForId = new PersonMongoByDatas(chosenPersonId, null);

        if (repository.findById(chosenPersonId).isPresent()) {

            PersonMongo person = service.find(datasForId);
            service.delete(datasForId);

            System.out.println(person);
            assertFalse(repository.existsById(chosenPersonId));
        }
        else {
            throw new IllegalStateException("PersonMongo with such id didn't present - " + chosenPersonId);
        }

    }

    @Test(priority = 1)
    public void testDeleteByPhoneNumber() {

        List<PersonMongo> persons = repository.findAll();
        Integer randomInt = faker.random().nextInt(1, 100);
        PersonMongo chosenPerson = persons.get(randomInt);
        String chosenPersonId = chosenPerson.getId();

        PersonMongoByDatas datasForId = new PersonMongoByDatas(chosenPersonId, null);

        if (repository.findById(chosenPersonId).isPresent()) {

            PersonMongo person = service.find(datasForId);
            PersonMongoByDatas datasForPhoneNumber = new PersonMongoByDatas(null, person.getPhoneNumber());

            service.delete(datasForPhoneNumber);

            System.out.println(person);
            assertFalse(repository.existsById(chosenPersonId));
        }
        else {
            throw new IllegalStateException("PersonMongo with such id didn't present - " + chosenPersonId);
        }
    }
}