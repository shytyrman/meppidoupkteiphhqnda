package com.example.meppidoupkteiphhqnda.controller;

import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.model.request.Person.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.PersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.service.PersonMongoCrud.PersonMongoCrudService;
import com.example.meppidoupkteiphhqnda.service.PersonMongoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/mongo")
public class PersonMongoController {

    private PersonMongoService personMongoService;
    private PersonMongoCrudService personMongoCrudService;

    @GetMapping(path = "/make")
    public void saveBasic() {
        personMongoService.make();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<?> get(@RequestBody PersonMongoByDatas request) {

        PersonMongo result = personMongoCrudService.find(request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestBody PersonMongoByDatas request) {

        personMongoCrudService.delete(request);

        //To-Do ResponceMessageClass
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}