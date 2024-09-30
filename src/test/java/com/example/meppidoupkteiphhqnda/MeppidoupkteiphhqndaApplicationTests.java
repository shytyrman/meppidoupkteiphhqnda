package com.example.meppidoupkteiphhqnda;

import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import com.example.meppidoupkteiphhqnda.service.PersonCrud.BasicPersonCrudService;
import com.example.meppidoupkteiphhqnda.service.PersonCrud.PersonCrudService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MeppidoupkteiphhqndaApplicationTests {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonCrudService service;

    Filter filter = new Filter(20, 20);
    @Test
    void contextLoads() {
        service.findAll(filter);
    }

}
