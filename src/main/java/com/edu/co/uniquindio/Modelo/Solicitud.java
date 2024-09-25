package com.edu.co.uniquindio.Modelo;


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
public class Solicitud implements Serializable {
    private String descripcion;
    private Especialidades especialidades;
    private Miembro miembro;

}
