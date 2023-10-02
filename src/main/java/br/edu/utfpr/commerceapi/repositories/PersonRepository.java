package br.edu.utfpr.commerceapi.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.commerceapi.models.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {
        Optional<Person> findByEmail(String email);
        Optional<Person> findByEmailAndSenha(String email, String senha);
        
}