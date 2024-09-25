package com.edu.co.uniquindio.Modelo;

import com.edu.co.uniquindio.Modelo.Enumeraciones.TiposDeMiembros;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Miembro extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private String correo;
    @Getter
    private Integer edad;
    @Getter
    private TiposDeMiembros tiposDeMiembros;

    private ArrayList<SesionesEntrenamiento> sesionesEntrenamientoEnUso = new ArrayList<>();

    public ArrayList<SesionesEntrenamiento> getSesionesEntrenamientoEnUso() {
        if (sesionesEntrenamientoEnUso == null) {
            sesionesEntrenamientoEnUso = new ArrayList<>();
        }
        return sesionesEntrenamientoEnUso;
    }


}
