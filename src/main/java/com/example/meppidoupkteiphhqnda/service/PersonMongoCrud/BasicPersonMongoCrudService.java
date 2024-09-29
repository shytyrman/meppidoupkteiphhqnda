package com.example.meppidoupkteiphhqnda.service.PersonMongoCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.PersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.UpdatePersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.repository.PersonMongoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BasicPersonMongoCrudService implements PersonMongoCrudService{

    private PersonMongoRepository repository;
    private MongoTemplate mongoTemplate;

    @Override
    public PersonMongo find(PersonMongoByDatas request) {

        String requestId = request.id();
        String requestPhoneNumber = request.phoneNumber();

        if (requestId == null && requestPhoneNumber == null) {
            return null;
        }

        PersonMongo result = null;

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
    public void delete(PersonMongoByDatas request) {

        String requestId = request.id();
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
    public List<PersonMongo> findAll(Filter filter) {
        if (filter.limit() == null || filter.offset() == null) {
            throw new IllegalStateException("Fields: limit, offset must not be null!");
        }
        return mongoTemplate.find(new Query().limit(filter.limit()).skip(filter.offset()), PersonMongo.class);
    }

    @Override
    public void update(UpdatePersonMongoByDatas request) {

        String requestId = request.searchId();
        String requestPhoneNumber = request.searchPhoneNumber();

        if (requestId == null && requestPhoneNumber == null) {
            return;
        }

        if (requestId != null && repository.existsById(requestId)) {

            PersonMongo personMongo = repository.findById(requestId).orElseThrow(EntityNotFoundException::new);

            updateFields(request, personMongo);
        }
        if (requestPhoneNumber != null && repository.existsByPhoneNumber(requestPhoneNumber)) {

            PersonMongo personMongo = repository.findByPhoneNumber(requestPhoneNumber).orElseThrow(EntityNotFoundException::new);

            updateFields(request, personMongo);
        }
    }

    private void updateFields(UpdatePersonMongoByDatas request, PersonMongo personMongo) {
        if (request.changeFullName() != null) {
            personMongo.setFullName(request.changeFullName());
        }
        if (request.changeBirthday() != null) {
            personMongo.setBirthday(request.changeBirthday());
        }
        if (request.changePhoneNumber() != null) {
            personMongo.setPhoneNumber(request.changePhoneNumber());
        }
        if (request.changePhoneNumberAdditional() != null) {
            personMongo.setPhoneNumberAdditional(request.changePhoneNumberAdditional());
        }
        repository.save(personMongo);
    }

}
