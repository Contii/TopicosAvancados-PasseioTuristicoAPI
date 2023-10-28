package br.edu.utfpr.commerceapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.commerceapi.dto.ReservaDTO;
import br.edu.utfpr.commerceapi.models.Reserva;
import br.edu.utfpr.commerceapi.repositories.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(UUID id) {
        return reservaRepository.findById(id);
        //.orElseThrow(() -> new RuntimeException("Person não encontrado"));
    }

    public Reserva create(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);
        return reservaRepository.save(reserva);
    }

    public Reserva update(UUID id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrado"));
        BeanUtils.copyProperties(reservaDTO, reserva);
        reserva.setUpdatedAt(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    public Boolean delete(UUID id) {
        reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrado"));
        reservaRepository.deleteById(id);
        return true;
    }

    //@transactional
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
}
