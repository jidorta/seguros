package com.ibandorta.seguros.seguros.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolizaCreateRequest {
    public String numeroPoliza;
    public String tipo;
    public BigDecimal prima;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;
    public Long usuarioId;

    public PolizaCreateRequest() {
    }

    public PolizaCreateRequest(String numeroPoliza, String tipo, BigDecimal prima, LocalDate fechaInicio, LocalDate fechaFin, Long usuarioId) {
        this.numeroPoliza = numeroPoliza;
        this.tipo = tipo;
        this.prima = prima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
