package com.ibandorta.seguros.seguros.controller;

import com.ibandorta.seguros.seguros.model.Poliza;
import com.ibandorta.seguros.seguros.model.Usuario;
import com.ibandorta.seguros.seguros.service.PolizaService;
import com.ibandorta.seguros.seguros.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PolizaService polizaService;


    public UsuarioController(UsuarioService usuarioService, PolizaService polizaService) {
        this.usuarioService = usuarioService;
        this.polizaService = polizaService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>>listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crear(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario>actualizar(@PathVariable Long id,
                                             @Valid @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/polizas")
    public ResponseEntity<List<Poliza>>polizasDeUsuario(@PathVariable Long id){
        return ResponseEntity.ok(polizaService.listarPorUsuario(id));
    }

}
