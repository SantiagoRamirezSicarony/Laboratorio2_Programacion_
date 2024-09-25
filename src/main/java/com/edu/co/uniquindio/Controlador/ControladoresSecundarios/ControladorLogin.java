package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;

import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class ControladorLogin  implements Initializable {
    @FXML
    private Button ButonConect;

    @FXML
    private Button ButonRegistro;

    @FXML
    private ComboBox<String> cb_Idioma;

    @FXML
    private Label labelIdentificacion;

    @FXML
    private Label labelNombre;



    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;






    @FXML
    void conectar(ActionEvent event) {

        try{

            Persona persona = controladorPrincipal.obtenerPersona(txtIdentificacion.getText(),txtNombre.getText());


                if(persona instanceof Administrador){
                    controladorPrincipal.guardarLog("A ingresado el administrador"
                                    + persona.getNumeroDeIdentificaciones()
                            ,Level.INFO);
                    usuarioLogueado.setUsuarioLogueado(persona);
                    controladorPrincipal.cerrarVentana(txtIdentificacion);
                    controladorPrincipal.navegar("PanelAdministrador.fxml","Panel Administrador");



                }else if(persona instanceof Miembro){
                    controladorPrincipal.guardarLog("A ingresado el miembro"
                                    + persona.getNumeroDeIdentificaciones()
                            ,Level.INFO);
                    usuarioLogueado.setUsuarioLogueado(persona);
                    controladorPrincipal.cerrarVentana(txtIdentificacion);
                    controladorPrincipal.navegar("PanelMiembro.fxml","Panel Miembro");

                }else{
                    controladorPrincipal.guardarLog("A ingresado el entrenador"
                                    + persona.getNumeroDeIdentificaciones()
                            ,Level.INFO);
                    usuarioLogueado.setUsuarioLogueado(persona);
                    controladorPrincipal.cerrarVentana(txtIdentificacion);
                    controladorPrincipal.navegar("PanelEntrenador.fxml","Panel Entrenador");
                }

        }catch(Exception e){

            controladorPrincipal.guardarLog(e.getMessage()
                    ,Level.SEVERE);

            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);

        }



    }

    @FXML
    void registrarCliente(ActionEvent event) {

        controladorPrincipal.navegar("RegistroInicial.fxml","RegistroInicial");

    }

    private SistemaDeportes sistemaDeportes;
    private Usuario usuarioLogueado;
    private ControladorPrincipal controladorPrincipal;




    /**
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.sistemaDeportes =  ControladorPrincipal.getInstance().getSistemaDeportes();
        Usuario.getInstancia().setUsuarioLogueado(ControladorPrincipal.getInstance().obtenerPersona(txtIdentificacion.getText(),txtNombre.getText()));
        this.usuarioLogueado = Usuario.getInstancia();
        controladorPrincipal = ControladorPrincipal.getInstance();

    }
}

