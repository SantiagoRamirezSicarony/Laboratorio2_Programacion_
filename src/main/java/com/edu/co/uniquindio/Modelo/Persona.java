package com.edu.co.uniquindio.Modelo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public  class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String numeroDeIdentificaciones;
}
