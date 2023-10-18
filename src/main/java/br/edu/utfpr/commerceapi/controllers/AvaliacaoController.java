package br.edu.utfpr.commerceapi.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.dto.AvaliacaoDTO;
import br.edu.utfpr.commerceapi.service.AvaliacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/avaliacao")
@CrossOrigin(origins = "*")
@Tag(name = "Avaliacao", description = "Avaliacao resource endpoints")
public class AvaliacaoController {
	@Autowired
    private AvaliacaoService avaliacaoService;

    // @Autowired
    // private RoleRepository roleRepository;  
    
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    //@SecurityRequirement(name = "Authorization")
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(avaliacaoService.findAll());
        // try {
        //     return ResponseEntity.ok(avaliacaoService.findAll());
	    // } catch (Exception e) {
		//     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// }
    }

    // @SecurityRequirement(name = "Authorization")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(avaliacaoService.findById(uuid));
    }

    // @SecurityRequirement(name = "Authorization")
    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody AvaliacaoDTO avaliacaoDto) {
        return ResponseEntity.ok(avaliacaoService.create(avaliacaoDto));
    }

    // @SecurityRequirement(name = "Authorization")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody AvaliacaoDTO avaliacaoDto) {
        return ResponseEntity.ok(avaliacaoService.update(UUID.fromString(id), avaliacaoDto));
    }

    // @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(avaliacaoService.delete(UUID.fromString(id)));
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}