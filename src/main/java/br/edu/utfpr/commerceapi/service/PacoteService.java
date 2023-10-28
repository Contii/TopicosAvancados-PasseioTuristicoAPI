package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.PacoteDTO;
import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.repositories.PacoteRepository;

@Service
public class PacoteService {
    @Autowired
    private PacoteRepository pacoteRepository;

    public List<Pacote> findAll() {
        return pacoteRepository.findAll();
    }

    public Optional<Pacote> findById(UUID id) {
        return pacoteRepository.findById(id);
        //.orElseThrow(() -> new RuntimeException("Pacote não encontrado"));
    }

    public List<Pacote> findByName(String name) {
        return pacoteRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return pacoteRepository.existsByName(name);
    }
    public Pacote create(PacoteDTO pacoteDto) {
        Pacote pacote = new Pacote();
        BeanUtils.copyProperties(pacoteDto, pacote);
        return pacoteRepository.save(pacote);
    }

    public Pacote update(UUID id, PacoteDTO pacoteDto) {
        Pacote pacote = pacoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Pacote não encontrado"));
        BeanUtils.copyProperties(pacoteDto, pacote);
        pacote.setUpdatedAt(LocalDateTime.now());
        return pacoteRepository.save(pacote);
    }

    public Boolean delete(UUID id) {
        pacoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Pacote não encontrado"));
        pacoteRepository.deleteById(id);
        return true;
    }

    //@transactional
    public Pacote save(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

}
