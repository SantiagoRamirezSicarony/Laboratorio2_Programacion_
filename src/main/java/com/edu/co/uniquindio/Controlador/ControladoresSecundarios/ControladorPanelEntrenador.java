package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;

import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.Entrenador;
import com.edu.co.uniquindio.Modelo.SesionesEntrenamiento;
import com.edu.co.uniquindio.Modelo.SistemaDeportes;
import com.edu.co.uniquindio.Modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ControladorPanelEntrenador  implements Initializable {


    @FXML
    private Button button_IrALogin;

    @FXML
    private Button button_VerInfo;

    @FXML
    private Label labelFecha_Sesiones;

    @FXML
    private Label label_DuracionSesion;

    @FXML
    private Label label_NombreEntrenador;

    @FXML
    private Label label_nombreDeporte;

    @FXML
    private TextArea tA_Descripcion;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> tC_IdentificadorSesion;

    @FXML
    private TableView<SesionesEntrenamiento> tV_IDSesion;

    @FXML
    void devolverse(ActionEvent event) {

        controladorPrincipal.navegar("Login.fxml","Login");
        controladorPrincipal.cerrarVentana(tA_Descripcion);
        usuarioLoqueado.cerrarSesion();

    }

    @FXML
    void verInformacion(ActionEvent event) {

        SesionesEntrenamiento sesion = tV_IDSesion.getSelectionModel().getSelectedItem();
        if (sesion != null) {
            label_nombreDeporte.setText(sesion.getDeporte().toString());
            labelFecha_Sesiones.setText(sesion.getFecha().toString());
            label_DuracionSesion.setText(sesion.getDuracion().toString());

        }else{
            label_nombreDeporte.setText("");
            labelFecha_Sesiones.setText("");
            label_DuracionSesion.setText("");

        }


    }

    ControladorPrincipal controladorPrincipal;
    SistemaDeportes sistemaDeportes;
    Usuario usuarioLoqueado;
    Entrenador entrenador;

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
        this.usuarioLoqueado = Usuario.getInstancia();
        this.entrenador = (Entrenador) usuarioLoqueado.getUsuarioLogueado();
        label_NombreEntrenador.setText(entrenador.getNombre());


        formatearTablas();
        llenarTabla();

    }

    public void formatearTablas(){

        tC_IdentificadorSesion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));

    }
    public void llenarTabla(){
        tV_IDSesion.getItems().clear();
        tV_IDSesion.getItems().addAll(entrenador.getSesiones());
    }


}