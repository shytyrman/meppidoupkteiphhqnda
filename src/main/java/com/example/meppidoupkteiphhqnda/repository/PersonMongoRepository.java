package com.example.meppidoupkteiphhqnda.repository;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMongoRepository extends MongoRepository<PersonMongo, Long> {
}
