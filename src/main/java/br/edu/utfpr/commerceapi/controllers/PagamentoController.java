package br.edu.utfpr.commerceapi.controllers;

import java.util.List;
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

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.service.PagamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
	@Autowired
    private PagamentoService pagamentoService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(pagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(pagamentoService.findById(uuid));
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody PagamentoDTO pagamentoDto) {
        return ResponseEntity.ok(pagamentoService.create(pagamentoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody PagamentoDTO pagamentoDto) {
        return ResponseEntity.ok(pagamentoService.update(UUID.fromString(id), pagamentoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(pagamentoService.delete(UUID.fromString(id)));
    }
}