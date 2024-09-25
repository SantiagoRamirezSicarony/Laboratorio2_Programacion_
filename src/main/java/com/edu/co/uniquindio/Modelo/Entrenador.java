package com.edu.co.uniquindio.Modelo;

import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Entrenador extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private Especialidades especialidad_Entrenador;
    private ArrayList<SesionesEntrenamiento> sesiones = new ArrayList<>();

}
