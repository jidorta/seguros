package com.ibandorta.seguros.seguros.repository;

import com.ibandorta.seguros.seguros.model.EstadoPoliza;
import com.ibandorta.seguros.seguros.model.Poliza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {
    Optional<Poliza>findByNumeroPoliza(String numeroPoliza);
    boolean existsByNumeroPoliza(String numeroPoliza);

    List<Poliza>findByEstado(EstadoPoliza estado);

    List<Poliza>findByNumeroPolizaContainingIgnoreCase(String numeroPoliza);

    Page<Poliza> findByEstado(EstadoPoliza estado, Pageable pageable);

    List<Poliza> findByUsuarioId(Long usuarioId);
}
