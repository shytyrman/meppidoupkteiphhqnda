package com.example.meppidoupkteiphhqnda.service.PersonCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.Person.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.Person.UpdatePersonByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BasicPersonCrudService implements PersonCrudService{

    private PersonRepository repository;
    @Override
    public Person find(PersonByDatas request) {

        Long requestId = request.id();
        String requestPhoneNumber = request.phoneNumber();

        if (requestId == null && requestPhoneNumber == null) {
            return null;
        }

        Person result = null;

        if (requestId != null && repository.existsById(requestId)) {
            result = repository.findById(requestId).orElseThrow(EntityNotFoundException::new);
        }
        else if (requestPhoneNumber != null && repository.existsByPhoneNumber(requestPhoneNumber)) {
            result = repository.findByPhoneNumber(requestPhoneNumber).orElseThrow(EntityNotFoundException::new);
        }

        return result;
    }

    @Override
    @Transactional
    public void delete(PersonByDatas request) {

        Long requestId = request.id();
        String requestPhoneNumber = request.phoneNumber();

        if (requestId == null && requestPhoneNumber == null) {
            return;
        }

        if (requestId != null && repository.existsById(requestId)) {
            repository.deleteById(requestId);
        }
        else if (requestPhoneNumber != null && repository.existsByPhoneNumber(requestPhoneNumber)) {
            repository.deleteByPhoneNumber(requestPhoneNumber);
        }
    }

    @Override
    public List<Person> findAll(Filter filter) {
        return repository.findAll(filter.limit(), filter.offset());
    }

    @Override
    public void update(UpdatePersonByDatas request) {

        Long requestId = request.searchId();
        String requestPhoneNumber = request.searchPhoneNumber();

        if (requestId == null && requestPhoneNumber == null) {
            return;
        }

        if ((requestId != null && repository.existsById(requestId)) || (requestPhoneNumber != null && repository.existsByPhoneNumber(requestPhoneNumber))) {
            updateBy(request);
        }

    }

    private void updateBy(UpdatePersonByDatas request) {
        repository.update(
                request.changeFullName(),
                request.changeBirthday(),
                request.changePhoneNumber(),
                request.changePhoneNumberAdditional(),
                request.searchId(),
                request.searchPhoneNumber()
        );
    }
}
