package com.example.meppidoupkteiphhqnda.repository;

import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonMongoRepository extends MongoRepository<PersonMongo, String> {

    public Optional<PersonMongo> findByPhoneNumber(String phoneNumber);
    public Boolean existsByPhoneNumber(String phoneNumber);
    public void deleteByPhoneNumber(String phoneNumber);
}
