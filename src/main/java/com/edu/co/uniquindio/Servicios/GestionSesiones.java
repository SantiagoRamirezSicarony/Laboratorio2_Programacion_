package com.edu.co.uniquindio.Servicios;

import com.edu.co.uniquindio.Modelo.Deporte;
import com.edu.co.uniquindio.Modelo.Entrenador;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import com.edu.co.uniquindio.Modelo.SesionesEntrenamiento;

import java.time.Duration;
import java.time.LocalDate;

public interface GestionSesiones {

    /**
     * Crea una nueva sesión de entrenamiento con los detalles especificados.
     *
     * @param fecha            La fecha de la sesión de entrenamiento.
     * @param duracion         La duración de la sesión.
     * @param estado           El estado de la sesión.
     * @param entrenadorSesion El entrenador asignado a la sesión.
     * @param deporte          La especialidad deportiva de la sesión.
     * @param descripcion
     * @throws Exception Si algún parámetro es inválido o no se puede crear la sesión.
     */
    void crearSesionesEntrenamiento(LocalDate fecha, String duracion,
                                    Estado estado, Entrenador entrenadorSesion, Deporte deporte,String descripcion) throws Exception;

    /**
     * Elimina una sesión de entrenamiento especificada.
     *
     * @param sesionesEntrenamiento La sesión de entrenamiento a eliminar.
     * @throws Exception Si la sesión no se encuentra o no se puede eliminar.
     */
    void eliminarSesiones(SesionesEntrenamiento sesionesEntrenamiento) throws Exception;

    /**
     * Genera un identificador único para una sesión de entrenamiento.
     *
     * @return Un identificador único para la sesión.
     */
    String generarIdentificador();

    /**
     * Crea un identificador de sesión aleatorio.
     *
     * @return Un identificador de sesión aleatorio de 6 dígitos.
     */
    String crearIdentificadorSesiones();

    /**
     * Valida si un identificador de sesión ya existe.
     *
     * @param identificador El identificador de sesión a validar.
     * @return El identificador si ya existe; de lo contrario, {@code null}.
     */
    String validarIdentificadorSesiones(String identificador);




}
