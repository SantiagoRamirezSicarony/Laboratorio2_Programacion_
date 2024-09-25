package com.edu.co.uniquindio.Modelo;
import lombok.*;

@Getter
@Setter
public class Usuario {


    private Persona usuarioLogueado;

    public static Usuario INSTANCIA;

    public Usuario (){

    }

    public static Usuario getInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new Usuario();
        }
        return INSTANCIA;
    }

    public void cerrarSesion(){
        usuarioLogueado = null;
    }
}
