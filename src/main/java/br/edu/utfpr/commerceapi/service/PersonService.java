package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.PersonDTO;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.repositories.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person não encontrado"));
    }

    public Person create(PersonDTO personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        return personRepository.save(person);
    }

    public Person update(UUID id, PersonDTO personDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person não encontrado"));
        BeanUtils.copyProperties(personDto, person);
        person.setUpdatedAt(LocalDateTime.now());
        return personRepository.save(person);
    }

    public Boolean delete(UUID id) {
        personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person não encontrado"));
        personRepository.deleteById(id);
        return true;
    }
}
