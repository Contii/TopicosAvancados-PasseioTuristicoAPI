package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> findById(UUID id) {
        return pagamentoRepository.findById(id);
        //.orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public Pagamento create(PagamentoDTO pagamentoDto) {
        Pagamento pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoDto, pagamento);
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento update(UUID id, PagamentoDTO pagamentoDto) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        BeanUtils.copyProperties(pagamentoDto, pagamento);
        pagamento.setUpdatedAt(LocalDateTime.now());
        return pagamentoRepository.save(pagamento);
    }

    public Boolean delete(UUID id) {
        pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamentoRepository.deleteById(id);
        return true;
    }
    
    //@transactional
    public Pagamento save(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }
}
