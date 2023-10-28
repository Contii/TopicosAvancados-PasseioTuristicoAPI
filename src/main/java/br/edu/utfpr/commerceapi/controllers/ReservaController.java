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
import br.edu.utfpr.commerceapi.dto.ReservaDTO;
import br.edu.utfpr.commerceapi.models.Reserva;
import br.edu.utfpr.commerceapi.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*")
@Tag(name = "Reserva", description = "Endpoints")
public class ReservaController {
	@Autowired
    private ReservaService reservaService;

    //================================== GET ==================================
    @Operation(summary = "Obtém lista de todas as reservas.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Reserva.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping(value = { "" })
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.ok(reservaService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //================================== GET ==================================
    @Operation(summary = "Obtém reserva por id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Reserva.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "400", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        var res = reservaService.findById(uuid);
        return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.status(404).body(Message.b("reserva não encontrada"));
    }
    //================================== POST ==================================
    @Operation(summary = "Insere uma nova reserva.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Criado com sucesso.", content = {@Content(schema = @Schema(implementation = Reserva.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ReservaDTO reservaDTO) {

        var reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        reserva.setCreatedAt(now);
        reserva.setUpdatedAt(now);

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(reservaService.save(reserva));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.b(e.getMessage()));
        }
    }
    //================================== PUT ==================================
    @Operation(summary = "Altera uma reserva pelo id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Alterado com sucesso.", content = {@Content(schema = @Schema(implementation = Reserva.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.update(UUID.fromString(id), reservaDTO));
    }
    //================================== DELETE ==================================
    @Operation(summary = "Deleta uma pessoa pelo id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletado com sucesso.", content = @Content(schema = @Schema(implementation = Reserva.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Id não existe."),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.ok(reservaService.delete(UUID.fromString(id)));
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