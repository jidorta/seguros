package com.ibandorta.seguros.seguros.DTO;

import com.ibandorta.seguros.seguros.model.EstadoPoliza;

public class ActualizarEstadoRequest {
    private EstadoPoliza estado;

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }
}
