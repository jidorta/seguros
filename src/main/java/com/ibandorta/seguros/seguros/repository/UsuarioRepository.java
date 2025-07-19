package com.ibandorta.seguros.seguros.repository;

import com.ibandorta.seguros.seguros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario>findByEmail(String email);
    boolean existsByEmail(String email);
}
