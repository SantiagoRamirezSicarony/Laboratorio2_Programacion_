package com.edu.co.uniquindio.Modelo;

import com.edu.co.uniquindio.Modelo.Enumeraciones.Dificultades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Deporte implements Serializable {
    private static final long serialVersionUID = 1L;
    private Especialidades especialidad;
    private String descripcion;
    private Dificultades dificultad;

}
