package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;
import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.*;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class ControladorPanelMiembro implements Initializable {


    @FXML
    private Button buttomEnviarSolicitud;

    @FXML
    private Button buttomInformacion_SesionesDisponibles;

    @FXML
    private Button buttomMarcarCompletado_SesionesDisponibles;

    @FXML
    private ComboBox<Especialidades> comboBoxDeporte_Solicitud;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> gridPane_sesionesDisponibles;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> gridPane_sesisionesCompletadas;

    @FXML
    private Label labelDescripcionDeporte_sesionesDisponibles;

    @FXML
    private Label labelDescripcionDeporte_sesisionesCompletadas;

    @FXML
    private Label labelDificultas_SesionesDisponibles;

    @FXML
    private Label labelDificultas_sesisionesCompletadas;

    @FXML
    private Label labelDuracion_SesionesDisponibles;

    @FXML
    private Label labelDuracion_sesisionesCompletadas;

    @FXML
    private Label labelEspecialidad_SesionesDisponibles;

    @FXML
    private Label labelEspecialidad_sesisionesCompletadas;

    @FXML
    private Label labelEstadoSesion_SesionesDisponibles;

    @FXML
    private Label labelEstadoSesion_sesisionesCompletadas;

    @FXML
    private Label labelFecha_SesionesDisponibles;

    @FXML
    private Label labelFecha_sesisionesCompletadas;

    @FXML
    private Label labelNombreEntrenador_SesionesDisponibles;

    @FXML
    private Label labelNombreEntrenadors_esisionesCompletadas;

    @FXML
    private TableView<SesionesEntrenamiento> tableView_SesionesDIsponibles;
    @FXML
    private TableView<SesionesEntrenamiento> tableView_SesionesCompletadas;

    @FXML
    private TextArea textAreaDescripcion_Solicitud;
    @FXML
    private Button buttonInformacionComletadas;




    private void marcarSesionCompletada() {
        SesionesEntrenamiento sesion = tableView_SesionesDIsponibles.getSelectionModel().getSelectedItem();
        sesion.setEstado(Estado.Completada);
        tableView_SesionesDIsponibles.getItems().remove(sesion);
        tableView_SesionesCompletadas.getItems().add(sesion);
        llenarTablaSecionesCompletas();
        llenarTablas();

        controladorPrincipal.guardarLog("El usuario" + usuario.getUsuarioLogueado().getNombre() + " A completado la sesion" + sesion.getID()
                , Level.INFO);
    }

    private void formaterTvSesionesProgremadas() {

    }


    private void llenarTablaSecionesCompletas() {
        tableView_SesionesCompletadas.getItems().clear();
        tableView_SesionesCompletadas.getItems().addAll(controladorPrincipal.getSistemaDeportes().obtenerListaSesionesCompletadas());
    }

    private void formaterTvSescionesCompletadas() {
        gridPane_sesionesDisponibles.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getID()));
    }


    private void mostrarInformacion_SesionesCompletadasLabels() {

        SesionesEntrenamiento sesionesEntrenamientoCompletado = tableView_SesionesCompletadas.getSelectionModel().getSelectedItem();

        labelNombreEntrenadors_esisionesCompletadas.setText(sesionesEntrenamientoCompletado.getEntrenadorSesion().getNombre());
        labelEstadoSesion_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getEstado().toString());
        labelFecha_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getFecha().toString());
        labelDuracion_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getDuracion().toString());
        labelEspecialidad_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getDeporte().getEspecialidad().toString());
        labelDescripcionDeporte_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getDeporte().getDificultad().toString());
        labelDificultas_sesisionesCompletadas.setText(sesionesEntrenamientoCompletado.getDeporte().getDescripcion());

    }

    private void mostrarInformacion_SesionesDisponiblesLabels() {

        SesionesEntrenamiento sesionesEntrenamiento = tableView_SesionesDIsponibles.getSelectionModel().getSelectedItem();

        labelNombreEntrenador_SesionesDisponibles.setText(sesionesEntrenamiento.getEntrenadorSesion().getNombre());
        labelEstadoSesion_SesionesDisponibles.setText(sesionesEntrenamiento.getEstado().toString());
        labelFecha_SesionesDisponibles.setText(sesionesEntrenamiento.getFecha().toString());
        labelDuracion_SesionesDisponibles.setText(sesionesEntrenamiento.getDuracion().toString());
        labelEspecialidad_SesionesDisponibles.setText(sesionesEntrenamiento.getDeporte().getEspecialidad().toString());
        labelDescripcionDeporte_sesionesDisponibles.setText(sesionesEntrenamiento.getDeporte().getDificultad().toString());
        labelDificultas_SesionesDisponibles.setText(sesionesEntrenamiento.getDeporte().getDescripcion());


    }

    private void modificarInformacion() {


        labelDescripcionDeporte_sesionesDisponibles.setText("");
        labelDificultas_sesisionesCompletadas.setText("");
        labelDificultas_SesionesDisponibles.setText("");
        labelDescripcionDeporte_sesisionesCompletadas.setText("");
        labelDuracion_SesionesDisponibles.setText("");
        labelDuracion_sesisionesCompletadas.setText("");
        labelEspecialidad_SesionesDisponibles.setText("");
        labelEspecialidad_sesisionesCompletadas.setText("");
        labelEstadoSesion_SesionesDisponibles.setText("");
        labelEstadoSesion_sesisionesCompletadas.setText("");
        labelFecha_SesionesDisponibles.setText("");
        labelFecha_sesisionesCompletadas.setText("");
        labelNombreEntrenador_SesionesDisponibles.setText("");
        labelNombreEntrenadors_esisionesCompletadas.setText("");
        textAreaDescripcion_Solicitud.setText("");





    }

    private void llenarTablas(){

        tableView_SesionesDIsponibles.getItems().clear();
        tableView_SesionesCompletadas.getItems().clear();

        gridPane_sesionesDisponibles.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getID()));
        gridPane_sesisionesCompletadas.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getID()));


        if (miembro.getSesionesEntrenamientoEnUso() != null) {
            for(SesionesEntrenamiento sesionesMiembro : miembro.getSesionesEntrenamientoEnUso()){


                if(sesionesMiembro.getEstado()== Estado.Programada){
                    tableView_SesionesDIsponibles.getItems().add(sesionesMiembro);
                }else{
                    tableView_SesionesCompletadas.getItems().add(sesionesMiembro);
                }

            }

        }



    }

    ControladorPrincipal controladorPrincipal;
    SistemaDeportes sistemaDeportes;
    Usuario usuario;
    Miembro miembro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controladorPrincipal = ControladorPrincipal.getInstance();
        this.sistemaDeportes = controladorPrincipal.getSistemaDeportes();
        this.usuario =  Usuario.getInstancia();
        this.miembro = (Miembro) usuario.getUsuarioLogueado();
        modificarInformacion();
        llenarTablas();
        llenarCombox();
    }

    private void llenarCombox() {
        comboBoxDeporte_Solicitud.getItems().clear();
        comboBoxDeporte_Solicitud.getItems().addAll(Especialidades.values());
    }

    public void marcarCompletada(javafx.event.ActionEvent actionEvent) {
        marcarSesionCompletada();
    }

    public void mostrarInformacion(javafx.event.ActionEvent actionEvent) {
        mostrarInformacion_SesionesDisponiblesLabels();

    }

    public void enviarSolicitud(javafx.event.ActionEvent actionEvent) throws Exception {
        controladorPrincipal.crearSolicitud(textAreaDescripcion_Solicitud.getText(),comboBoxDeporte_Solicitud.getValue(), miembro);
        textAreaDescripcion_Solicitud.setText("");
        try {
            controladorPrincipal.guardarLog("Se ha generado una solicitud a partir de "
                    , Level.INFO);

        }catch (Exception e){
            controladorPrincipal.guardarLog("Al momento de generar una solicitud se genero una excepcion" + e.getMessage()
                    ,Level.SEVERE);
        }
    }

    public void mostrarInformacion_SesionesCompletadas(javafx.event.ActionEvent actionEvent) {

        mostrarInformacion_SesionesCompletadasLabels();


    }

    public void retroceder(ActionEvent actionEvent) {
        controladorPrincipal.navegar("Login.fxml","menu");
        controladorPrincipal.cerrarVentana(comboBoxDeporte_Solicitud);
    }
}





