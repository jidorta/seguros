package com.ibandorta.seguros.seguros.service;

import com.ibandorta.seguros.seguros.model.Usuario;
import com.ibandorta.seguros.seguros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crear(Usuario u){
        if(usuarioRepository.existsByEmail(u.getEmail())){
            throw new IllegalArgumentException("Ya existe un usuario con email " +  u.getEmail());
        }
        return usuarioRepository.save(u);
    }


    @Transactional
    public Usuario actualizar(Long id, Usuario datos){
        Usuario u = obtener(id);
        if(!u.getEmail().equals(datos.getEmail()) &&
           usuarioRepository.existsByEmail(datos.getEmail())){
            throw new IllegalArgumentException("Ya existe un usuario con email " + datos.getEmail());
        }
        u.setNombre(datos.getNombre());
        u.setEmail(datos.getEmail());
        return usuarioRepository.save(u);
    }

    @Transactional
    public void eliminar(Long id){
        usuarioRepository.delete(obtener(id));
    }


    public Usuario obtener(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
