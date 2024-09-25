package com.edu.co.uniquindio.Servicios;

import com.edu.co.uniquindio.Modelo.*;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Dificultades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public interface ServiciosSistema {

    void guardarLog(String msg, Level nivel) throws IOException;
    /**
     * Carga los datos persistidos del sistema.
     *
     * @throws Exception Si ocurre un error al cargar los datos.
     */
    void cargarDatos() throws Exception;

    /**
     * Guarda los datos actuales del sistema.
     *
     * @throws Exception Si ocurre un error al guardar los datos.
     */
    void guardarDatos() throws Exception;

    /**
     * Valida las credenciales de usuario proporcionadas.
     *
     * @param identificador El identificador del usuario.
     * @param nombre        El nombre del usuario.
     * @return La persona correspondiente al identificador proporcionado si es válida.
     * @throws Exception Si los datos de acceso son incorrectos.
     */
    Persona validarUsuario(String identificador, String nombre) throws Exception;

    Persona obtenerPersona(String identificador, String nombre);

    /**
     * Crea y registra un nuevo entrenador en el sistema.
     *
     * @param nombre                 El nombre del entrenador.
     * @param numeroDeIdentificacion El número de identificación del entrenador.
     * @param especialidades         La especialidad del entrenador.
     * @param sesiones               La lista de sesiones de entrenamiento asignadas al entrenador.
     * @throws Exception Si los datos ingresados no son correctos.
     */
    Entrenador crearEntrenador(String nombre, String numeroDeIdentificacion, Especialidades especialidades, ArrayList<SesionesEntrenamiento> sesiones) throws Exception;

    /**
     * Crea un nuevo miembro en el sistema.
     *
     * @param identificador El identificador del miembro.
     * @param nombre        El nombre del miembro.
     * @param correo        El correo del miembro.
     * @param edad          La edad del miembro.
     * @throws Exception Si los datos ingresados no son correctos.
     */
    Miembro crearMiembro(String identificador, String nombre, String correo, Integer edad) throws Exception;

    /**
     * Crea una nueva sesión de entrenamiento.
     *
     * @param fecha            La fecha de la sesión de entrenamiento.
     * @param duracion         La duración de la sesión.
     * @param estado           El estado de la sesión.
     * @param entrenadorSesion El entrenador asignado a la sesión.
     * @param deporte          La especialidad deportiva de la sesión.
     * @throws Exception Si algún parámetro es inválido o no se puede crear la sesión.
     */
    void crearSesionesEntrenamiento(LocalDate fecha, String duracion, Estado estado, Entrenador entrenadorSesion, Deporte deporte,String descripcion) throws Exception;

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

    /**
     * Crea un nuevo deporte en el sistema.
     *
     * @param especialidad La especialidad del deporte.
     * @param dificultad   La dificultad del deporte.
     * @param descripcion  La descripción del deporte.
     * @return El objeto {@code Deporte} creado.
     * @throws Exception Si algún parámetro es inválido.
     */
    Deporte crearDeporte(Especialidades especialidad, Dificultades dificultad, String descripcion) throws Exception;

    /**
     * Crea una nueva solicitud y la agrega a la lista de solicitudes de miembros.
     *
     * @param descripcion   La descripción de la solicitud.
     * @param especialidades La especialidad (o deporte) que el miembro desea practicar. No puede ser nulo.
     * @param miembro       El miembro que realiza la solicitud.
     * @throws Exception    Si la especialidad es nula, se lanza una excepción con un mensaje de error.
     */
    void crearSolicitud(String descripcion, Especialidades especialidades, Miembro miembro)throws Exception;




}
