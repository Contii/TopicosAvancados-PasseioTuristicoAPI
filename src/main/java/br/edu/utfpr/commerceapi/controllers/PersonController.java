package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;
// import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

// import br.edu.utfpr.commerceapi.dto.Message;
import br.edu.utfpr.commerceapi.dto.PersonDTO;
import br.edu.utfpr.commerceapi.models.Person;
// import br.edu.utfpr.commerceapi.models.RoleName;
import br.edu.utfpr.commerceapi.repositories.PersonRepository; // remover?
import br.edu.utfpr.commerceapi.repositories.RoleRepository;
import br.edu.utfpr.commerceapi.service.PersonService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
@Tag(name = "Person", description = "Person resource endpoints")
public class PersonController {
	@Autowired
	PersonRepository personRepository; // remover?

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	/*============= Obtem todas as pessoas do banco em paginas. =============*/
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/pages")
    public ResponseEntity<Page<Person>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(personService.findAll(pageable));
    }

	// @SecurityRequirement(name = "Authorization")
    // @GetMapping
    // public ResponseEntity<Page<Person>> getAll(
    //         @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
    //     return ResponseEntity.ok().body(personService.findAll(pageable));
    // }

    // @Operation(summary = "Retrieve a Person by Id", description = "Get a Person object by specifying its id. The response is Person object with id, name, email and birth.", tags = {
    //         "Person" })
    // @ApiResponses({
    //         @ApiResponse(responseCode = "200", content = {
    //                 @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
    //         @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    //         @ApiResponse(responseCode = "500")
    // })

	/**============= Obtem todas as pessoas do banco em lista. ============*/
	@SecurityRequirement(name = "Authorization")
	@GetMapping(value = {"", "/"})
	public ResponseEntity<List<Person>> getAll() {
		try {
			List<Person> pessoas = personRepository.findAll();
			return new ResponseEntity<>(pessoas, HttpStatus.OK);
		  } catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	
	/**====================== Obtem 1 pessoa pelo ID. =====================*/
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		try{
		Optional<Person> personOpt = personRepository.findById(UUID.fromString(id));
			if (personOpt.isPresent()) {
				return ResponseEntity.ok(personOpt.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// @SecurityRequirement(name = "Authorization")
    // @GetMapping("/{id}")
    // public ResponseEntity<Object> getById(@PathVariable UUID id) {
    //     Optional<Person> person = this.personService.findById(id);

    //     return person.isPresent()
    //             ? ResponseEntity.ok(person.get())
    //             : ResponseEntity.notFound().build();
    // }

	/**===================== Obtem 1 pessoa pelo nome. ====================*/
	@SecurityRequirement(name = "Authorization")
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

	/**========================= Cria uma pessoa. =========================*/
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Object> create(@RequestBody PersonDTO personDTO) {
		var pes = new Person(); // pessoa para persistir no DB
		BeanUtils.copyProperties(personDTO, pes);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body( personRepository.save(pes) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar pessoa.");
		}
	}

	// @PostMapping
    // public ResponseEntity<Object> create(@RequestBody PersonDTO personDTO) {
    //     if (this.personService.existsByEmail(personDTO.getEmail())) {
    //         return ResponseEntity
    //                 .status(HttpStatus.SEE_OTHER)
    //                 .body("Conflict: E-mail exists.");
    //     }

    //     var person = new Person();
    //     BeanUtils.copyProperties(personDTO, person);

    //     LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
    //     person.setCreatedAt(now);
    //     person.setUpdatedAt(now);
    //     person.setPassword(passwordEncoder.encode(personDTO.getPassword()));

    //     // Adicionando o papel padrão para a pessoa
    //     var role = roleRepository.findByName(RoleName.USER);
    //     if (role.isPresent())
    //         person.addRole(role.get());

    //     try {
    //         return ResponseEntity
    //                 .status(HttpStatus.CREATED)
    //                 .body(personService.save(person));
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.b(e.getMessage()));
    //     }
    // }

	/**==================== Atualiza 1 pessoa pelo ID. ====================*/
	@SecurityRequirement(name = "Authorization")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody PersonDTO personDTO) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}	
		//buscando no bando de dados
		var person = personRepository.findById(uuid);
		//verifica se ela existe
		if (person.isEmpty())
			return ResponseEntity.notFound().build();
		var personToUpdate = person.get();
		BeanUtils.copyProperties(personDTO, personToUpdate);
		personToUpdate.setUpdatedAt(LocalDateTime.now());
		try {
			return ResponseEntity.ok().body( personRepository.save(personToUpdate) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar a pessoa.");
		}
	}

	// @SecurityRequirement(name = "Authorization")
    // @PutMapping("/{id}")
    // public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody PersonDTO personDTO) {
    //     var person = this.personService.findById(id);

    //     if (person.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     // Verificar se já existe uma pessoa com este e-mail
    //     if (this.personService.existsByEmail(personDTO.getEmail())
    //             && !this.personService.existsByIdAndEmail(person.get().getId(), personDTO.getEmail())) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(Message.b("Conflito: O e-mail já está em uso"));
    //     }

    //     // Criar um novo objeto, copiar os valores do DTO e setar os atributos imutáveis
    //     var personToUpdate = new Person();
    //     BeanUtils.copyProperties(personDTO, personToUpdate);
    //     personToUpdate.setId(person.get().getId());
    //     personToUpdate.setCreatedAt(person.get().getCreatedAt());
    //     personToUpdate.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

    //     return ResponseEntity.ok().body(this.personService.save(personToUpdate));
    // }

	/**===================== Deleta 1 pacote pelo ID. =====================*/
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}	
		var person = personRepository.findById(uuid);
		if (person.isEmpty())
			return ResponseEntity.notFound().build();
		try {
			personRepository.delete(person.get());
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(e.getMessage());
		}
	}
	// @SecurityRequirement(name = "Authorization")
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Object> delete(@PathVariable UUID id) {
    //     var person = personService.findById(id);

    //     if (person.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     personService.delete(person.get());
    //     return ResponseEntity.ok().build();
    // }
	
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