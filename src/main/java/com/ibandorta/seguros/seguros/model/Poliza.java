package com.ibandorta.seguros.seguros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="polizas")
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El número de poliza es obligatorio")
    @Column(name="numero_poliza",unique = true, nullable = false)
    private String numeroPoliza;

    @NotBlank(message="El tipo de póliza es obligatorio")
    private String tipo;

    @Positive(message = "la prima debe ser mayor que 0")
    private BigDecimal prima;

    @NotNull(message = "la fecha de inicio es obligatoria")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="fecha_inicio")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_fin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoPoliza estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Poliza() {
    }


    public Poliza(Long id, String numeroPoliza, String tipo, BigDecimal prima, LocalDate fechaInicio, LocalDate fechaFin, EstadoPoliza estado, Usuario usuario) {
        this.id = id;
        this.numeroPoliza = numeroPoliza;
        this.tipo = tipo;
        this.prima = prima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }
}
