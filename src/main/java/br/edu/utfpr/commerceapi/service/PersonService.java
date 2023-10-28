package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
        //.orElseThrow(() -> new RuntimeException("Person não encontrado"));
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
    
    public Optional<Person> findByEmailAndPassword(String email, String password) {
        return personRepository.findByEmailAndPassword(email, password);
    }

    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    public boolean existsByIdAndEmail(UUID id, String email) {
        return personRepository.existsByIdAndEmail(id, email);
    }

    public boolean existsByEmailAndPassword(String email, String password) {
        return personRepository.existsByEmailAndPassword(email, password);
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

    //@transactional
    public Boolean delete(UUID id) {
        personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person não encontrado"));
        personRepository.deleteById(id);
        return true;
    }

    //@transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }
}