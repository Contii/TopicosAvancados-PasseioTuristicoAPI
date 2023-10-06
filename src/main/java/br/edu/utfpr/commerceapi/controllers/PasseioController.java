package br.edu.utfpr.commerceapi.controllers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.commerceapi.dto.PasseioDTO;
import br.edu.utfpr.commerceapi.service.PasseioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/passeio")
public class PasseioController {
	@Autowired
    private PasseioService passeioService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(passeioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(passeioService.findById(uuid));
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody PasseioDTO passeioDto) {
        return ResponseEntity.ok(passeioService.create(passeioDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody PasseioDTO passeioDto) {
        return ResponseEntity.ok(passeioService.update(UUID.fromString(id), passeioDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(passeioService.delete(UUID.fromString(id)));
    }
}