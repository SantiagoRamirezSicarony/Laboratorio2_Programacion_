package com.edu.co.uniquindio.Modelo;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Administrador extends Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    private String accessCode;

}
