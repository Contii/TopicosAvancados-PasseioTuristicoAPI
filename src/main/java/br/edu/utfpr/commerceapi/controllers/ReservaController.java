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
import br.edu.utfpr.commerceapi.dto.ReservaDTO;
import br.edu.utfpr.commerceapi.service.ReservaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
    private ReservaService reservaService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(reservaService.findById(uuid));
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody ReservaDTO reservaDto) {
        return ResponseEntity.ok(reservaService.create(reservaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody ReservaDTO reservaDto) {
        return ResponseEntity.ok(reservaService.update(UUID.fromString(id), reservaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(reservaService.delete(UUID.fromString(id)));
    }
}