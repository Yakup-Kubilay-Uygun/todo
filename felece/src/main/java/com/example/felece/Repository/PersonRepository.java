package com.example.felece.Repository;

import com.example.felece.Model.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PersonRepository extends CrudRepository <Person, Long>{
    Person findByEmail(String email);
}
