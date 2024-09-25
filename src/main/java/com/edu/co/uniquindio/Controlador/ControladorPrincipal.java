package com.edu.co.uniquindio.Controlador;

import com.edu.co.uniquindio.app.App;
import com.edu.co.uniquindio.Modelo.*;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Dificultades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import com.edu.co.uniquindio.Servicios.ServiciosSistema;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

@Getter

public class ControladorPrincipal implements ServiciosSistema{


    private final SistemaDeportes sistemaDeportes;

    public static ControladorPrincipal INSTANCE;


    private ControladorPrincipal() {
        this.sistemaDeportes = new SistemaDeportes();
    }


    ///Metodo para crear y retornar la intancia del controlador
    public static ControladorPrincipal getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ControladorPrincipal();

        }
        return INSTANCE;
    }

    ///Metodo para mostrar alertas

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta - Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    ///Metodo para navegar entre ventanas y retornar un FXMLLOADER
    public FXMLLoader navegar(String nombreVista, String titulo){
        try {

            FXMLLoader loader = new FXMLLoader(App.class.getResource(nombreVista));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titulo);

            // Mostrar la nueva ventana
            stage.show();

            return loader;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    /**
     * @param msg
     * @param nivel
     */
    @Override
    public void guardarLog(String msg, Level nivel) {
        sistemaDeportes.guardarLog(msg,nivel);
    }

    @Override
    public void cargarDatos() throws Exception {
        sistemaDeportes.guardarDatos();

    }

    @Override
    public void guardarDatos() throws Exception {
        sistemaDeportes.guardarDatos();
    }

    @Override
    public Persona validarUsuario(String identificador, String nombre) throws Exception {
        return sistemaDeportes.validarUsuario(identificador, nombre);
    }

    @Override
    public Persona obtenerPersona(String identificador, String nombre) {
        return sistemaDeportes.obtenerPersona(identificador, nombre);
    }

    @Override
    public Entrenador crearEntrenador(String nombre, String numeroDeIdentificacion, Especialidades especialidades, ArrayList<SesionesEntrenamiento> sesiones) throws Exception {
        return sistemaDeportes.crearEntrenador( nombre,  numeroDeIdentificacion,  especialidades,  sesiones);
    }

    @Override
    public Miembro crearMiembro(String identificador, String nombre, String correo, Integer edad) throws Exception {
        return sistemaDeportes.crearMiembro(identificador, nombre, correo, edad);
    }


    @Override
    public void crearSesionesEntrenamiento(LocalDate fecha, String duracion, Estado estado, Entrenador entrenadorSesion, Deporte deporte,String descripcion ) throws Exception {
         sistemaDeportes.crearSesionesEntrenamiento( fecha,  duracion, estado, entrenadorSesion, deporte,descripcion);
    }

    @Override
    public void eliminarSesiones(SesionesEntrenamiento sesionesEntrenamiento) throws Exception {
        sistemaDeportes.eliminarSesiones(sesionesEntrenamiento);

    }

    @Override
    public String generarIdentificador() {
        return sistemaDeportes.generarIdentificador();
    }

    @Override
    public String crearIdentificadorSesiones() {
        return sistemaDeportes.crearIdentificadorSesiones();
    }

    @Override
    public String validarIdentificadorSesiones(String identificador) {
        return sistemaDeportes.validarIdentificadorSesiones(identificador);
    }

    @Override
    public Deporte crearDeporte(Especialidades especialidad, Dificultades dificultad, String descripcion) throws Exception {
        return sistemaDeportes.crearDeporte(especialidad, dificultad, descripcion);
    }

    @Override
    public void crearSolicitud(String descripcion, Especialidades especialidades, Miembro miembro) throws Exception {

        sistemaDeportes.crearSolicitud(descripcion, especialidades, miembro);

    }


}
