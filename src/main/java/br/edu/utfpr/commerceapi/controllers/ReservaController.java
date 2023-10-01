package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.dto.ReservaDTO;
import br.edu.utfpr.commerceapi.models.Reserva;
import br.edu.utfpr.commerceapi.repositories.ReservaRepository;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	ReservaRepository reservaRepository;

	/*============= Obtem todas as reservas do banco em lista. =============*/
	@GetMapping("/pages")
	public ResponseEntity<Page<Reserva>> getAllPage(@PageableDefault(
		page=0, size=10, direction = Sort.Direction.ASC) Pageable pageable)
		{
			return ResponseEntity.ok( reservaRepository.findAll(pageable));
		}
	/**============= Obtem todas as reservas do banco em lista. ============*/
	@GetMapping(value = { "", "/" })
		public List<Reserva> getAll() {
			return reservaRepository.findAll();
		}
	/**====================== Obtem 1 reserva pelo ID. =====================*/
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		Optional<Reserva> reservaOpt = reservaRepository.findById(UUID.fromString(id));
		return reservaOpt.isPresent() ? ResponseEntity.ok(reservaOpt.get()) : ResponseEntity.notFound().build();
	}


	/**========================= Cria uma reserva. =========================*/
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Object> create(@RequestBody ReservaDTO reservaDTO) {
		var res = new Reserva(); // reserva para persistir no DB
		BeanUtils.copyProperties(reservaDTO, res);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body( reservaRepository.save(res) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar reserva.");
		}
	}


	/**==================== Atualiza 1 reserva pelo ID. ====================*/
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody ReservaDTO reservaDTO) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}		
		//buscando no bando de dados
		var reserva = reservaRepository.findById(uuid);
		//verifica se ela existe
		if (reserva.isEmpty())
			return ResponseEntity.notFound().build();
		var reservaToUpdate = reserva.get();
		BeanUtils.copyProperties(reservaDTO, reservaToUpdate);
		reservaToUpdate.setUpdatedAt(LocalDateTime.now());
		try {
			return ResponseEntity.ok().body( reservaRepository.save(reservaToUpdate) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar reserva.");
		}
	}


	/**===================== Deleta 1 pacote pelo ID. =====================*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}	
		var reserva = reservaRepository.findById(uuid);
		if (reserva.isEmpty())
			return ResponseEntity.notFound().build();
		try {
			reservaRepository.delete(reserva.get());
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(e.getMessage());
		}
	}
}