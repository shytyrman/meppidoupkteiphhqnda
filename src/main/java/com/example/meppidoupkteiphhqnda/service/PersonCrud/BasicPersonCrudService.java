package com.example.meppidoupkteiphhqnda.service.PersonCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.PersonByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasicPersonCrudService implements PersonCrudService{

    private PersonRepository repository;
    @Override
    public Person find(PersonByDatas request) {

        Long requestId = request.id();
        String requestPhoneNumber = request.phoneNumber();

        Person result = null;

        if (requestId != null) {
            result = repository.findById(requestId).orElseThrow(EntityNotFoundException::new);
        }
        else if (requestPhoneNumber != null) {
            result = repository.findByPhoneNumber(requestPhoneNumber).orElseThrow(EntityNotFoundException::new);
        }

        return result;
    }

    @Override
    public void delete(PersonByDatas request) {

    }

    @Override
    public List<Person> findAll(Filter filter) {
        return null;
    }

    @Override
    public void update(PersonByDatas request) {

    }
}
