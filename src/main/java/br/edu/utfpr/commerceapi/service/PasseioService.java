package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.PasseioDTO;
import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.repositories.PasseioRepository;

@Service
public class PasseioService {
    @Autowired
    private PasseioRepository passeioRepository;

    public List<Passeio> findAll() {
        return passeioRepository.findAll();
    }

    public Passeio findById(UUID id) {
        return passeioRepository.findById(id).orElseThrow(() -> new RuntimeException("Passeio não encontrado"));
    }

    public Passeio create(PasseioDTO passeioDto) {
        Passeio passeio = new Passeio();
        BeanUtils.copyProperties(passeioDto, passeio);
        return passeioRepository.save(passeio);
    }

    public Passeio update(UUID id, PasseioDTO passeioDto) {
        Passeio passeio = passeioRepository.findById(id).orElseThrow(() -> new RuntimeException("Passeio não encontrado"));
        BeanUtils.copyProperties(passeioDto, passeio);
        passeio.setUpdatedAt(LocalDateTime.now());
        return passeioRepository.save(passeio);
    }

    public Boolean delete(UUID id) {
        passeioRepository.findById(id).orElseThrow(() -> new RuntimeException("Passeio não encontrado"));
        passeioRepository.deleteById(id);
        return true;
    }
}
