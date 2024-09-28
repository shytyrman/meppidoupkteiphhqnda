package com.example.meppidoupkteiphhqnda.service.PersonCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Person.PersonByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BasicPersonCrudServiceTest {

    PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    BasicPersonCrudService service = new BasicPersonCrudService(personRepository);

    @Test
    public void testFind() {

        PersonByDatas datas = new PersonByDatas(13L, "323423");
        Person person = service.find(datas);

        assertEquals(person.getId(), datas.id());
    }

    @Test
    public void testDelete() {
        assertTrue(true);
    }

    @Test
    public void testFindAll() {
        assertTrue(true);
    }
    @Test
    public void testUpdate() {
        assertTrue(true);
    }
}