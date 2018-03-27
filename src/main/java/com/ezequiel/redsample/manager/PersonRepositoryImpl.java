package com.ezequiel.redsample.manager;

import com.ezequiel.redsample.entity.Person;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private static String KEY = "Person";

    private RedisTemplate<String, Person> redisTemplate;
    private HashOperations hashOperations;

    public PersonRepositoryImpl(RedisTemplate<String, Person> redisTemplate){
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Person person) {
        hashOperations.put(KEY, person.getId(), person);
    }

    @Override
    public Map<String, Person> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public Person findById(Integer id) {
        return (Person)hashOperations.get(KEY, id);
    }

    @Override
    public void update(Person person) {
        save(person);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete(KEY, id);
    }
}
