package com.example.meppidoupkteiphhqnda.service.PersonCrud;

import com.example.meppidoupkteiphhqnda.model.Person;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.PersonByDatas;
import com.example.meppidoupkteiphhqnda.model.request.UpdatePersonByDatas;

import java.util.List;

public interface PersonCrudService {
    public Person find(PersonByDatas request);
    public void delete(PersonByDatas request);
    public List<Person> findAll(Filter filter);
    public void update(UpdatePersonByDatas request);
}
