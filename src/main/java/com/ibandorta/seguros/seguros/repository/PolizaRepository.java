package com.ibandorta.seguros.seguros.repository;

import com.ibandorta.seguros.seguros.model.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {
    Optional<Poliza>findByNumeroPoliza(String numeroPoliza);
    boolean existsByNumeroPoliza(String numeroPoliza);
}
