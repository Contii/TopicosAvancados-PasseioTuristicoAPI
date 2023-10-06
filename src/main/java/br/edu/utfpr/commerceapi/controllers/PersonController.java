package br.edu.utfpr.commerceapi.controllers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.commerceapi.dto.PersonDTO;
import br.edu.utfpr.commerceapi.service.PersonService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {
	@Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(personService.findById(uuid));
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody PersonDTO personDto) {
        return ResponseEntity.ok(personService.create(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody PersonDTO personDto) {
        return ResponseEntity.ok(personService.update(UUID.fromString(id), personDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(personService.delete(UUID.fromString(id)));
    }
}