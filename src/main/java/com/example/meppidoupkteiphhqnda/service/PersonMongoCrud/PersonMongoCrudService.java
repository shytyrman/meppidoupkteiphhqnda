package com.example.meppidoupkteiphhqnda.service.PersonMongoCrud;

import com.example.meppidoupkteiphhqnda.model.PersonMongo;
import com.example.meppidoupkteiphhqnda.model.request.Filter;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.UpdatePersonMongoByDatas;
import com.example.meppidoupkteiphhqnda.model.request.PersonMongo.PersonMongoByDatas;

import java.util.List;

public interface PersonMongoCrudService {
    public PersonMongo find(PersonMongoByDatas request);
    public void delete(PersonMongoByDatas request);
    public List<PersonMongo> findAll(Filter filter);
    public void update(UpdatePersonMongoByDatas request);
}
