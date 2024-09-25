package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;

import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.SistemaDeportes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorRegistroInicial implements Initializable {
    @FXML
    private Button buttomSerEntrenador;

    @FXML
    private Button buttomSerMiembro;

        ControladorPrincipal controladorPrincipal;
        SistemaDeportes sistemaDeportes;


    public void irAentrenador(ActionEvent actionEvent) {

        controladorPrincipal.navegar("RegistroEntrenador.fxml","RegistroEntrenador");
        controladorPrincipal.cerrarVentana(buttomSerEntrenador);


    }


    /**
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.controladorPrincipal = ControladorPrincipal.getInstance();
        this.sistemaDeportes= controladorPrincipal.getSistemaDeportes();


    }

    public void irAMiembro(ActionEvent actionEvent) {
        controladorPrincipal.navegar("RegistroMiembro.fxml","RegistroMiembro");
        controladorPrincipal.cerrarVentana(buttomSerEntrenador);
    }

    public void retroceder(ActionEvent actionEvent) {
        controladorPrincipal.navegar("Login.fxml","menu");
        controladorPrincipal.cerrarVentana(buttomSerMiembro);
    }
}
