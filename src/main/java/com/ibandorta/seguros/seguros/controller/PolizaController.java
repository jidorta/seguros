package com.ibandorta.seguros.seguros.controller;

import com.ibandorta.seguros.seguros.model.Poliza;
import com.ibandorta.seguros.seguros.service.PolizaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {
    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public ResponseEntity<List<Poliza>>listar(){
        return ResponseEntity.ok( polizaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza>obtener(@PathVariable Long id){
        return ResponseEntity.ok(polizaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Poliza>crear(@Valid @RequestBody Poliza poliza){
        return ResponseEntity.ok(polizaService.crear(poliza));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poliza> actualizar(@PathVariable Long id,
                                             @Valid @RequestBody Poliza poliza){
        return ResponseEntity.ok(polizaService.actualizar(id,poliza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        polizaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



}
