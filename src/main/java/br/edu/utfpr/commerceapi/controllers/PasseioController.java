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
import br.edu.utfpr.commerceapi.dto.PasseioDTO;
import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.service.PasseioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/passeio")
@CrossOrigin(origins = "*")
@Tag(name = "Passeio", description = "Endpoints")
public class PasseioController {
	@Autowired
    private PasseioService passeioService;

    //================================== GET ==================================
    @Operation(summary = "Obtém lista de todos os passeios.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Person.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.ok(passeioService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //================================== GET ==================================
    @Operation(summary = "Obtém passeio por id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Passeio.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "400", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        var res = passeioService.findById(uuid);
        return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.status(404).body(Message.b("passeio não encontrado"));
    }
    //================================== POST ==================================
    @Operation(summary = "Insere uma nova pessoa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Criado com sucesso.", content = {@Content(schema = @Schema(implementation = Passeio.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "303", description = "Nome ja está sendo utilizado."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody PasseioDTO passeioDTO) {
        if (this.passeioService.existsByName(passeioDTO.getName())) {
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .body("Conflict: Nome existente.");
        }
        var passeio = new Passeio();
        BeanUtils.copyProperties(passeioDTO, passeio);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        passeio.setCreatedAt(now);
        passeio.setUpdatedAt(now);

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(passeioService.save(passeio));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.b(e.getMessage()));
        }
    }
    //================================== PUT ==================================
    @Operation(summary = "Altera um passeio pelo id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Alterado com sucesso.", content = {@Content(schema = @Schema(implementation = Passeio.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
   @SecurityRequirement(name = "Authorization")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody PasseioDTO passeioDTO) {
        return ResponseEntity.ok(passeioService.update(UUID.fromString(id), passeioDTO));
    }
    //================================== DELETE ==================================
    @Operation(summary = "Deleta um passeio pelo id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletado com sucesso.", content = @Content(schema = @Schema(implementation = Person.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(passeioService.delete(UUID.fromString(id)));
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