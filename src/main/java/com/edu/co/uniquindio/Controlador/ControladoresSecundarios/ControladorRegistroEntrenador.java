package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;
import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.Entrenador;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.SistemaDeportes;
import com.edu.co.uniquindio.Modelo.Usuario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class ControladorRegistroEntrenador  implements Initializable {
    @FXML
    private Button ButonRegistroEntrenador;

    @FXML
    private Button buttonRegresarVentana;

    @FXML
    private ComboBox<Especialidades> comboBoxEspecialidad;

    @FXML
    private TextField txtRegistroIdentificacion;

    @FXML
    private TextField txtRegistroNombre;

    @FXML
    void registrarUsuario(ActionEvent event) throws Exception {

        try {
            Entrenador entrenador = controladorPrincipal.crearEntrenador(txtRegistroNombre.getText(), txtRegistroIdentificacion.getText(), comboBoxEspecialidad.getValue(), new ArrayList<>());
            controladorPrincipal.guardarLog("Se ha creado un entrenador con el nombre " + entrenador.getNombre()
                    , Level.INFO);
            usuario.setUsuarioLogueado(entrenador);

            controladorPrincipal.navegar("PanelEntrenador.fxml", "PanelEntrenador");
            controladorPrincipal.cerrarVentana(txtRegistroIdentificacion);

        }catch (Exception e){

            controladorPrincipal.guardarLog("Ha ocurrido algo al momento de crear el entrenador, la exception es: " + e.getMessage()
                    ,Level.SEVERE);

        }
    }

    @FXML
    void regresarVentana(ActionEvent event) {
        controladorPrincipal.navegar("RegistroInicial.fxml","RegistroInicial");
        controladorPrincipal.cerrarVentana(txtRegistroIdentificacion);

    }

    ControladorPrincipal controladorPrincipal;
    SistemaDeportes  sistemaDeportes;
    Usuario usuario;

    /**
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controladorPrincipal = ControladorPrincipal.getInstance();
        this.sistemaDeportes = controladorPrincipal.getSistemaDeportes();
        this.usuario = Usuario.getInstancia();
        llenarComboBoxEspecialidad();
    }

    private void llenarComboBoxEspecialidad() {
        this.comboBoxEspecialidad.getItems().clear();
        comboBoxEspecialidad.getItems().addAll(Especialidades.values());
    }

    public void regresar(ActionEvent actionEvent) {
        controladorPrincipal.navegar("Login.fxml","menu");
        controladorPrincipal.cerrarVentana(txtRegistroIdentificacion);
    }
}
