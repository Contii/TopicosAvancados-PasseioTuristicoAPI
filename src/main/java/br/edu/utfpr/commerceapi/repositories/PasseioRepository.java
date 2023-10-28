package br.edu.utfpr.commerceapi.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.commerceapi.models.Passeio;

public interface PasseioRepository extends JpaRepository<Passeio, UUID> {

        public List<Passeio> findByName(String name);
    
        public boolean existsByName(String name);

}