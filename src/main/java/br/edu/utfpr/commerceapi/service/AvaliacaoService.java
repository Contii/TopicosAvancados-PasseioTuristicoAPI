package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.AvaliacaoDTO;
import br.edu.utfpr.commerceapi.models.Avaliacao;
import br.edu.utfpr.commerceapi.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao findById(UUID id) {
        return avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliacao não encontrado"));
    }

    public Avaliacao create(AvaliacaoDTO avaliacaoDto) {
        Avaliacao avaliacao = new Avaliacao();
        BeanUtils.copyProperties(avaliacaoDto, avaliacao);
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao update(UUID id, AvaliacaoDTO avaliacaoDto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliacao não encontrado"));
        BeanUtils.copyProperties(avaliacaoDto, avaliacao);
        avaliacao.setUpdatedAt(LocalDateTime.now());
        return avaliacaoRepository.save(avaliacao);
    }

    public Boolean delete(UUID id) {
        avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliacao não encontrado"));
        avaliacaoRepository.deleteById(id);
        return true;
    }
}