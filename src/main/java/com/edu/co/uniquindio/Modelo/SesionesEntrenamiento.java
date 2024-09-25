package com.edu.co.uniquindio.Modelo;

import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import lombok.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SesionesEntrenamiento implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String duracion;
    private Estado estado;
    private Entrenador entrenadorSesion;
    private Deporte deporte;
    private String ID;
    private String descripcion;

}
