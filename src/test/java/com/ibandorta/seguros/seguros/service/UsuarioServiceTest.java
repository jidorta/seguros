package com.ibandorta.seguros.seguros.service;

import com.ibandorta.seguros.seguros.model.EstadoPoliza;
import com.ibandorta.seguros.seguros.model.Poliza;
import com.ibandorta.seguros.seguros.model.Usuario;
import com.ibandorta.seguros.seguros.repository.PolizaRepository;
import com.ibandorta.seguros.seguros.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PolizaRepository polizaRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private PolizaService polizaService;

    private Poliza polizaBase;

    private Usuario usuario;

    @BeforeEach
    void setup(){
        polizaBase = new Poliza();
        polizaBase.setId(1L);
        polizaBase.setNumeroPoliza("POL-12345");
        polizaBase.setTipo("Vida");
        polizaBase.setPrima(new BigDecimal(500.00));
        polizaBase.setEstado(EstadoPoliza.ACTIVA);
    }

    @Test
    void crearUsuario_ok(){
        when(usuarioRepository.existsByEmail("iband@gmail.com")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            u.setId(99L);
            return u;
        });

        Usuario creado = usuarioService.crear(usuario);

        assertThat(creado.getId()).isNotNull();
        assertThat(creado.getEmail()).isEqualTo("iband@gmail.com");
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void crear_fechaFinAntesQueInicio_lanzaExcepcioon(){
        Poliza p = new Poliza();
        p.setNumeroPoliza("POL-222");
        p.setTipo("Auto");
        p.setPrima(BigDecimal.valueOf(200.0));
        p.setFechaInicio(LocalDate.of(2025,7,20));
        p.setFechaFin(LocalDate.of(2025,7,10));

        assertThatThrownBy(()->polizaService.crear(p))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fecha fin");
        verify(polizaRepository,never()).save(any());

    }

    @Test
    void listarPorEstado_devuelveLista(){
        when(polizaRepository.findByEstado(EstadoPoliza.ACTIVA))
                .thenReturn(List.of(polizaBase));
        List<Poliza> result = polizaService.listarPorEstado(EstadoPoliza.ACTIVA);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNumeroPoliza()).isEqualTo("POL-12345");
    }

    @Test
    void cancelar_cambiaEstado(){
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(polizaBase));
        when(polizaRepository.save(any(Poliza.class))).thenAnswer(inv->inv.getArgument(0));

        Poliza cancelada = polizaService.cancelar(1L);

        assertThat(cancelada.getEstado()).isEqualTo(EstadoPoliza.CANCELADA);
        verify(polizaRepository).save(polizaBase);
    }


}
