package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import br.edu.utfpr.commerceapi.dto.Message;
import br.edu.utfpr.commerceapi.dto.PersonDTO;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.models.RoleName;
import br.edu.utfpr.commerceapi.repositories.RoleRepository;
import br.edu.utfpr.commerceapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
@Tag(name = "Person", description = "Endpoints")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //================================== GET ==================================
    @Operation(summary = "Obter lista de todas as pessoas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping(value = { "", "/" })
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.ok(personService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //================================== GET ==================================
    @Operation(summary = "Obter pessoa por id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        var res = personService.findById(uuid);
        return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.status(404).body(Message.b("pessoa não encontrada"));
    }
    //================================== POST ==================================
    @Operation(summary = "Inserir uma nova pessoa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Criado com sucesso.", content = {@Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "303", description = "Email ja está sendo utilizado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PersonDTO personDTO) {
        if (this.personService.existsByEmail(personDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .body("Conflict: E-mail exists.");
        }

        var person = new Person();
        BeanUtils.copyProperties(personDTO, person);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        person.setCreatedAt(now);
        person.setUpdatedAt(now);
        person.setPassword(passwordEncoder.encode(personDTO.getPassword()));

        // Adicionando o papel padrão para a pessoa
        var role = roleRepository.findByName(RoleName.USER);
        if (role.isPresent())
        person.addRole(role.get());

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(personService.save(person));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.b(e.getMessage()));
        }
    }
    //================================== PUT ==================================
    @Operation(summary = "Alterar uma pessoa pelo Id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Alterado com sucesso.", content = {@Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody PersonDTO personDto) {
        return ResponseEntity.ok(personService.update(UUID.fromString(id), personDto));
    }
    //================================== DELETE ==================================
    @Operation(summary = "Deletar uma pessoa pelo Id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletado com sucesso.", content = @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(personService.delete(UUID.fromString(id)));
    }
    //================================== EXCEPTIONS ==================================
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