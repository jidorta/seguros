package com.ibandorta.seguros.seguros.controller;

import com.ibandorta.seguros.seguros.DTO.ActualizarEstadoRequest;
import com.ibandorta.seguros.seguros.DTO.PolizaCreateRequest;
import com.ibandorta.seguros.seguros.model.EstadoPoliza;
import com.ibandorta.seguros.seguros.model.Poliza;
import com.ibandorta.seguros.seguros.model.Usuario;
import com.ibandorta.seguros.seguros.service.PolizaService;
import com.ibandorta.seguros.seguros.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {
    private final PolizaService polizaService;
    private final UsuarioService usuarioService;

    public PolizaController(PolizaService polizaService, UsuarioService usuarioService) {
        this.polizaService = polizaService;
        this.usuarioService = usuarioService;
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
    public ResponseEntity<Poliza>crear(@Valid @RequestBody PolizaCreateRequest req){

        Poliza poliza = new Poliza();
        poliza.setNumeroPoliza(req.numeroPoliza);
        poliza.setTipo(req.tipo);
        poliza.setPrima(req.prima);
        poliza.setFechaInicio(req.fechaInicio);
        poliza.setFechaFin(req.fechaFin);

        Usuario u = usuarioService.obtener(req.usuarioId);
        poliza.setUsuario(u);

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

    @PutMapping("/{id}/estado")
    public ResponseEntity<Poliza> actualizarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest request){

        Poliza polizaActualizada = polizaService.actualizarEstado(id,request.getEstado());
        return ResponseEntity.ok(polizaActualizada);
    }


    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Poliza>>listarporEstado(@PathVariable EstadoPoliza estado){
        return ResponseEntity.ok(polizaService.listarPorEstado(estado));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Poliza>>buscar(
            @RequestParam(required = false)String numero){

        if (numero !=null && !numero.isBlank()){
            return ResponseEntity.ok(polizaService.buscarPorNumero(numero));
        }
        return ResponseEntity.ok(polizaService.listar());
    }

    @GetMapping("/estado/{estado}/page")
    public ResponseEntity<Page<Poliza>>listarPorEstadoPaginado(
            @PathVariable EstadoPoliza estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc")String sort){

        //sort = "campo,direccion
        String[] parts = sort.split(",");
        String sortField = parts[0];
        String sortDir = parts.length > 1 ? parts[1] : "asc";

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                page, size,
                sortDir.equalsIgnoreCase("desc")
                        ? org.springframework.data.domain.Sort.by(sortField).descending()
                        : org.springframework.data.domain.Sort.by(sortField).ascending()
        );

        return ResponseEntity.ok(polizaService.listarPorEstadoPaginado(estado,pageable));
    }



}
