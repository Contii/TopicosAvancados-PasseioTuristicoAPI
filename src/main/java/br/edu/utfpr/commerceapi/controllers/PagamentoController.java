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

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
	@Autowired
	PagamentoRepository pagamentoRepository;

	/*============= Obtem todos os pagamentos do banco em lista. =============*/
	@GetMapping("/pages")
	public ResponseEntity<Page<Pagamento>> getAllPage(@PageableDefault(
		page=0, size=10, direction = Sort.Direction.ASC) Pageable pageable)
		{
			return ResponseEntity.ok( pagamentoRepository.findAll(pageable));
		}
	/**============= Obtem todos os pagamentos do banco em lista. ============*/
	@GetMapping(value = { "", "/" })
		public List<Pagamento> getAll() {
			return pagamentoRepository.findAll();
		}
	/**====================== Obtem 1 pagamento pelo ID. =====================*/
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		Optional<Pagamento> pagamentoOpt = pagamentoRepository.findById(UUID.fromString(id));
		return pagamentoOpt.isPresent() ? ResponseEntity.ok(pagamentoOpt.get()) : ResponseEntity.notFound().build();
	}


	/**========================= Cria um pagamento. =========================*/
	@PostMapping(value = { "", "/" })
	public ResponseEntity<Object> create(@RequestBody PagamentoDTO pagamentoDTO) {
		var pag = new Pagamento(); // pagamento para persistir no DB
		BeanUtils.copyProperties(pagamentoDTO, pag);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body( pagamentoRepository.save(pag) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar pagamento.");
		}
	}


	/**==================== Atualiza 1 pagamento pelo ID. ====================*/
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody PagamentoDTO pagamentoDTO) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}	
		//buscando no bando de dados
		var pagamento = pagamentoRepository.findById(uuid);
		//verifica se ela existe
		if (pagamento.isEmpty())
			return ResponseEntity.notFound().build();
		var pagamentoToUpdate = pagamento.get();
		BeanUtils.copyProperties(pagamentoDTO, pagamentoToUpdate);
		pagamentoToUpdate.setUpdatedAt(LocalDateTime.now());
		try {
			return ResponseEntity.ok().body( pagamentoRepository.save(pagamentoToUpdate) );
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar pagamento.");
		}
	}


	/**===================== Deleta 1 pagamento pelo ID. =====================*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch(Exception e) { 
			return ResponseEntity.badRequest()
			.body("Formato de UUID inválido.");
		}	
		var pagamento = pagamentoRepository.findById(uuid);
		if (pagamento.isEmpty())
			return ResponseEntity.notFound().build();
		try {
			pagamentoRepository.delete(pagamento.get());
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(e.getMessage());
		}
	}
}