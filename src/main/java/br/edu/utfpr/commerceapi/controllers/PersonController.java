package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.repositories.PersonRepository;

@RestController
@RequestMapping("/pessoa")
public class PersonController {
	@Autowired
	PersonRepository personRepository;

	/** Obtem todas as pessoas do banco. */
	@GetMapping(value = { "", "/" })
	public String getAll() {
		return "Aqui estao todas as pessoas.";
	}

	/** Obtem 1 pessoa pelo ID. */
	@GetMapping("/{id}")
	public String getById(@PathVariable Long id) {
		return "Aqui esta a pessoa." + id;
	}

	/** Cria uma pessoa. */
	@PostMapping(value = { "", "/" })
	public Person create() {
		var pes = new Person();
		pes.setNome("Juca da silva");
		pes.setEmail("Juca@hotmail.com");
		pes.setNascimento(LocalDateTime.now());

		personRepository.save(pes);

				return pes;
	}

	/** Atualiza 1 pessoa pelo ID. */
	@PutMapping("/{id}")
	public String update(@PathVariable Long id) {
		return "Pessoa" + id + "atualizada.";
	}

	/** Deleta 1 pessoa pelo ID. */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		return id + "Deletado.";
	}

}
