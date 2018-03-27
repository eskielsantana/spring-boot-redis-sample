package com.ezequiel.redsample.manager;

import com.ezequiel.redsample.entity.Person;

import java.util.Map;

public interface PersonRepository {

    void save(Person person);
    Map<String, Person> findAll();
    Person findById(Integer id);
    void update(Person person);
    void delete(Integer id);

}
