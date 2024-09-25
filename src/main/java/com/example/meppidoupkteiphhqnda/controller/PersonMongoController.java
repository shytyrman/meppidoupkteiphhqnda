package com.example.meppidoupkteiphhqnda.controller;

import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.repository.PersonMongoRepository;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import com.example.meppidoupkteiphhqnda.service.PersonMongoService;
import com.example.meppidoupkteiphhqnda.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PersonMongoController {
    private PersonMongoRepository personMongoRepository;
    private PersonMongoService personMongoService;

    @GetMapping(path = "/makeMongo")
    public void saveBasic() {
        personMongoService.make();
    }
}