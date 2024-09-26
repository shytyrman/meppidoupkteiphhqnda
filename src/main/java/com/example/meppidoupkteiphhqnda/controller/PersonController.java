package com.example.meppidoupkteiphhqnda.controller;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.UpdatePersonByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import com.example.meppidoupkteiphhqnda.service.PersonCrud.PersonCrudService;
import com.example.meppidoupkteiphhqnda.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

    private PersonRepository personRepository;
    private PersonCrudService personCrudService;
    private PersonService personService;

    @GetMapping(path = "/make")
    public void saveBasic() {
        personService.make();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<?> get(@RequestBody PersonByDatas request) {

        Person result = personCrudService.find(request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(@RequestBody Filter filter) {

        List<Person> result = personCrudService.findAll(filter);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestBody PersonByDatas request) {

        personCrudService.delete(request);

        //To-Do ResponceMessageClass
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdatePersonByDatas request) {

        try {
            personCrudService.update(request);
        }
        catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
