package com.ibandorta.seguros.seguros.service;

import com.ibandorta.seguros.seguros.model.Poliza;
import com.ibandorta.seguros.seguros.repository.PolizaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;

    public PolizaService(PolizaRepository polizaRepository) {
        this.polizaRepository = polizaRepository;
    }

    @Transactional(readOnly = true)
    public List<Poliza>listar(){
        return polizaRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Poliza obtenerPorId(Long id){
        return polizaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Poliza no encontrada con id " + id));
    }

    @Transactional
    public Poliza crear(Poliza poliza){
        validarFechas(poliza);
        if(polizaRepository.existsByNumeroPoliza(poliza.getNumeroPoliza())){
            throw new IllegalArgumentException("El numero de poliza ya existe: " +poliza.getNumeroPoliza());
        }
        return polizaRepository.save(poliza);
    }

    @Transactional
    public Poliza actualizar(Long id, Poliza datos){
        validarFechas(datos);
        Poliza existente = obtenerPorId(id);
        //Si cambia el número, comprobar duplicados
        if (!existente.getNumeroPoliza().equals(datos.getNumeroPoliza())
                && polizaRepository.existsByNumeroPoliza(datos.getNumeroPoliza())){
            throw new IllegalArgumentException("el número de poliza ya existe: " + datos.getNumeroPoliza());
        }
        existente.setNumeroPoliza(datos.getNumeroPoliza());
        existente.setTipo(datos.getTipo());
        existente.setPrima(datos.getPrima());
        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());
        return polizaRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id){
        Poliza p = obtenerPorId(id);
        polizaRepository.delete(p);
    }

    private void validarFechas(Poliza p){
        if (p.getFechaInicio() != null && p.getFechaFin() != null
                && p.getFechaFin().isBefore(p.getFechaInicio())) {
    throw new IllegalArgumentException("la fecha fin debe ser posterior a la fecha inicio");
        }
    }
}
