package com.jmjbrothers.componentgroupdemo.service;

import com.jmjbrothers.componentgroupdemo.entity.Person;
import com.jmjbrothers.componentgroupdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person save(Person person) {



        return personRepository.save(person);
    }
}
