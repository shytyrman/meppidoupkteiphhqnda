package com.example.meppidoupkteiphhqnda.repository;

import com.example.meppidoupkteiphhqnda.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    public Optional<Person> findByPhoneNumber(String phoneNumber);
    public Boolean existsByPhoneNumber(String phoneNumber);
    public void deleteByPhoneNumber(String phoneNumber);
}
