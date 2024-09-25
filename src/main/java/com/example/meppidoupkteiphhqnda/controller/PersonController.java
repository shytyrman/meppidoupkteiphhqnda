package com.example.meppidoupkteiphhqnda.controller;

import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import com.example.meppidoupkteiphhqnda.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PersonController {

    private PersonRepository personRepository;
    private PersonService personService;

    @GetMapping(path = "/make")
    public void saveBasic() {
        personService.make();
    }
}
