package com.example.meppidoupkteiphhqnda.service.PersonCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.Person.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.Person.UpdatePersonByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

@ActiveProfiles("test")
@SpringBootTest
//@DataMongoTest
//@AutoConfigureDataMongo
//@ExtendWith(SpringExtension.class)
public class BasicPersonCrudServiceTest extends AbstractTestNGSpringContextTests {


    @Autowired
    PersonCrudService service;

    @Autowired
    PersonRepository repository;

    Faker faker = new Faker();

    @Test
    public void searchById() {

        Long randomLong = faker.random().nextLong(1, 100);
        PersonByDatas datas = new PersonByDatas(randomLong , null);

        Person personFromService = service.find(datas);
        Person personFromRepository = repository.findById(randomLong).get();

        System.out.println(personFromService);
        System.out.println(personFromRepository);

        assertEquals(personFromService, personFromRepository);
    }

    @Test
    public void searchByPhoneNumber() {

        Long randomLong = faker.random().nextLong(1, 100);
        PersonByDatas datasForId = new PersonByDatas(randomLong, null);

        Person personFromServiceById = service.find(datasForId);

        PersonByDatas datasForPhoneNumber = new PersonByDatas(null, personFromServiceById.getPhoneNumber());
        Person personFromServiceByPhoneNumber = service.find(datasForPhoneNumber);

        System.out.println(personFromServiceById);
        System.out.println(personFromServiceByPhoneNumber);

        assertEquals(personFromServiceById, personFromServiceByPhoneNumber);
    }


    @Test
    public void testFindAll() {

        Integer limit = faker.random().nextInt(0, 100);
        Integer offset = faker.random().nextInt(0, 100);

        Filter filter = new Filter(limit, offset);

        List<Person> persons = service.findAll(filter);

        assertTrue(persons.size() <= limit && persons.get(0).getId() > offset);

    }

    @Test
    public void testUpdateSomeFieldsById() {

        Long randomLong = faker.random().nextLong(1, 100);

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

        System.out.println(repository.findById(randomLong));

        UpdatePersonByDatas updateDatasForId = new UpdatePersonByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, randomLong, null);

        service.update(updateDatasForId);

        Person person = repository.findById(randomLong).get();

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

        Long randomLong = faker.random().nextLong(1, 100);

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

        System.out.println(repository.findById(randomLong));

        UpdatePersonByDatas updateDatasForId = new UpdatePersonByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, randomLong, null);

        service.update(updateDatasForId);

        Person person = repository.findById(randomLong).get();

        assertTrue(
                changeFullName.equals(person.getFullName()) &&
                        changeBirthday.equals(person.getBirthday()) &&
                        changePhoneNumber.equals(person.getPhoneNumber()) &&
                        changeAdditionalPhoneNumber.equals(person.getPhoneNumberAdditional())
        );
    }

    @Test
    public void testUpdateSomeFieldsPhoneNumber() {

        Long randomLong = faker.random().nextLong(1, 100);

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

        System.out.println(repository.findById(randomLong));

        Person personBefore = repository.findById(randomLong).get();

        UpdatePersonByDatas updateDatasForPhoneNumber = new UpdatePersonByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, null, personBefore.getPhoneNumber());

        service.update(updateDatasForPhoneNumber);

        Person person = repository.findById(randomLong).get();


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

        Long randomLong = faker.random().nextLong(1, 100);

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

        System.out.println(repository.findById(randomLong));

        Person personBefore = repository.findById(randomLong).get();

        UpdatePersonByDatas updateDatasForPhoneNumber = new UpdatePersonByDatas(changeFullName, changeBirthday, changePhoneNumber, changeAdditionalPhoneNumber, null, personBefore.getPhoneNumber());

        service.update(updateDatasForPhoneNumber);

        Person person = repository.findById(randomLong).get();

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

    @Test
    public void testDeleteById() {

        Long randomLong = faker.random().nextLong(1, 100);
        PersonByDatas datasForId = new PersonByDatas(randomLong, null);

        if (repository.findById(randomLong).isPresent()) {

            Person person = service.find(datasForId);
            service.delete(datasForId);

            System.out.println(person);
            assertFalse(repository.existsById(randomLong));
        }
        else {
            throw new IllegalStateException("Person with such id didn't present - " + randomLong);
        }

    }

    @Test
    public void testDeleteByPhoneNumber() {

        Long randomLong = faker.random().nextLong(1, 100);
        PersonByDatas datasForId = new PersonByDatas(randomLong, null);

        if (repository.findById(randomLong).isPresent()) {

            Person person = service.find(datasForId);
            PersonByDatas datasForPhoneNumber = new PersonByDatas(null, person.getPhoneNumber());

            service.delete(datasForPhoneNumber);

            System.out.println(person);
            assertFalse(repository.existsById(randomLong));
        }
        else {
            throw new IllegalStateException("Person with such id didn't present - " + randomLong);
        }
    }

}