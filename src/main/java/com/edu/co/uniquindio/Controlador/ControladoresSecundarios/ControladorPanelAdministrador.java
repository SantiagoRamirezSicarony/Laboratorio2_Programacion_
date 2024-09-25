package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;

import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.Entrenador;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Dificultades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Especialidades;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Estado;
import com.edu.co.uniquindio.Modelo.Enumeraciones.Idiomas;
import com.edu.co.uniquindio.Modelo.Miembro;
import com.edu.co.uniquindio.Modelo.SesionesEntrenamiento;
import com.edu.co.uniquindio.Modelo.Solicitud;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class ControladorPanelAdministrador  implements Initializable {


    @FXML
    private Button bt_VerInformacion;

    @FXML
    private Button buttonActualizar;

    @FXML
    private Button buttonAsignar;

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonCrear;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonLimpiarCampos;

    @FXML
    private AnchorPane crear_sesion;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private Label labelDescripcion;

    @FXML
    private Label labelDescripcionSolicitud;

    @FXML
    private Label labelDificultad;

    @FXML
    private Label labelDuracion;

    @FXML
    private Label labelEspecialidad;

    @FXML
    private Label labelEspecialidades;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelFechaDuracion;

    @FXML
    private Label labelFechaEntrenador;

    @FXML
    private Label labelFechaSesion;

    @FXML
    private Button modificarLenguaje;

    @FXML
    private TextArea tf_Descripcion;

    @FXML
    private TextArea tf_DescripcionSolicitud;

    @FXML
    private TextField tf_duracion;


    @FXML
    private Label txt_duracion;

    @FXML
    private Label txt_entrenador;

    @FXML
    private Label txt_especialidad;

    @FXML
    private Label txt_fecha;


    @FXML
    private ComboBox<Especialidades> cb_Especialidades;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> ctv_descripcion_sesiones;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> ctv_duracion_sesion;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> ctv_entrenador_sesion;

    @FXML
    private TableColumn<Solicitud, String> ctv_especialidadSesion;

    @FXML
    private TableColumn<Entrenador, String> ctv_especialidad_entredor;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> ctv_especialidad_sesion;

    @FXML
    private TableColumn<SesionesEntrenamiento, LocalDate> ctv_fecha_sesiones;

    @FXML
    private TableColumn<SesionesEntrenamiento, String> ctv_id;

    @FXML
    private TableColumn<Solicitud, String> ctv_nombreSeccion;

    @FXML
    private TableColumn<Entrenador, String> ctv_nombre_entrenador;

    @FXML
    private TableView<Entrenador> tv_lista_entreadores;

    @FXML
    private TableView<SesionesEntrenamiento> tv_sesionesSolicitudes;

    @FXML
    private TableView<SesionesEntrenamiento> tv_sesiones_creadas;

    @FXML
    private TableView<Solicitud> tv_lista_solicitudes;

    @FXML
    private ComboBox<Dificultades> cB_DificultadSesiones;
    @FXML
    private ComboBox<Idiomas> comoboBoxLenguajes;

    ControladorPrincipal controladorPrincipal;


    @FXML
    void actualizar_sesiones(ActionEvent event) {


    }

    @FXML
    void asignar_sesion_miembro(ActionEvent event) {
        SesionesEntrenamiento sesion = tv_sesionesSolicitudes.getSelectionModel().getSelectedItem();
        Miembro miembro = tv_lista_solicitudes.getSelectionModel().getSelectedItem().getMiembro();
        miembro.getSesionesEntrenamientoEnUso().add(sesion);
        controladorPrincipal.guardarLog("Se ha asignado una sesion a "+ miembro.getNombre(), Level.INFO);

    }

    @FXML
    void crear_sesion(ActionEvent event) throws Exception {

        controladorPrincipal.crearSesionesEntrenamiento(dp_fecha.getValue(),tf_duracion.getText(), Estado.Programada, tv_lista_entreadores.getSelectionModel().getSelectedItem()
                , controladorPrincipal.crearDeporte(cb_Especialidades.getSelectionModel().getSelectedItem(),cB_DificultadSesiones.getSelectionModel().getSelectedItem(),tf_Descripcion.getText()),tf_Descripcion.getText());
        actualizarTablas();

        controladorPrincipal.guardarLog("Se ha creado una sesion", Level.INFO);

    }

    private void actualizarTablas() {
        llenarTvListaSesionesCreadas();
        llenarTvListaEntrenadores();
        llenarTvSesionesSolicitudes();
        llenarTvListaSolicitudes();
    }

    @FXML
    void eliminar_sesiones(ActionEvent event) {

        SesionesEntrenamiento sesionesEntrenamientos = tv_sesiones_creadas.getSelectionModel().getSelectedItem();
        controladorPrincipal.getSistemaDeportes().getListaSecionesEnElsistema().remove(sesionesEntrenamientos);
        actualizarTablas();
        controladorPrincipal.guardarLog("Se ha eliminado una sesion"
                ,Level.INFO);
    }

    @FXML
    void limpiarCampos(ActionEvent event) {

        dp_fecha.setChronology(null);
        tf_Descripcion.setText("");
        tf_duracion.setText("");


    }

    /**
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controladorPrincipal= ControladorPrincipal.getInstance();
        formatearColumnasTvListaSolicitudes();
        formatearColumnasTvListaEntranadores();
        formatearColumnasTvListaSesionesCreadas();
        formatearColumnasTvSesionesSolicitudes();
        llenarTvListaSolicitudes();
        llenarTvListaEntrenadores();
        llenarTvListaSesionesCreadas();
        llenarTvSesionesSolicitudes();
        llenarCombox();
        llenarComboxDificultad();
        llenarComboBoxLenguajes();


    }

    private void llenarComboxDificultad() {
        cB_DificultadSesiones.getItems().clear();
        cB_DificultadSesiones.getItems().addAll(Dificultades.values());
    }

    private void llenarCombox() {
        cb_Especialidades.getItems().clear();
        cb_Especialidades.getItems().addAll(Especialidades.values());
    }

    private void llenarComboBoxLenguajes(){
      comoboBoxLenguajes.getItems().clear();
      comoboBoxLenguajes.getItems().addAll(Idiomas.values());
    }

    private void llenarTvSesionesSolicitudes() {
        tv_sesionesSolicitudes.getItems().addAll(controladorPrincipal.getSistemaDeportes().getListaSecionesEnElsistema());
    }

    private void llenarTvListaSesionesCreadas() {
        tv_sesiones_creadas.getItems().clear();
        tv_sesiones_creadas.getItems().addAll(controladorPrincipal.getSistemaDeportes().getListaSecionesEnElsistema());
    }

    private void llenarTvListaEntrenadores() {
        tv_lista_entreadores.getItems().clear();
        tv_lista_entreadores.getItems().addAll(controladorPrincipal.getSistemaDeportes().getListaEntrenadores());
    }

    private void llenarTvListaSolicitudes() {
        tv_lista_solicitudes.getItems().addAll(controladorPrincipal.getSistemaDeportes().getListaSolicitudesMiembros());
    }

    private void formatearColumnasTvSesionesSolicitudes() {
        ctv_id.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));

    }

    private void formatearColumnasTvListaSesionesCreadas() {
        ctv_fecha_sesiones.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ctv_descripcion_sesiones.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ctv_duracion_sesion.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getDuracion()));
        ctv_especialidad_sesion.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getEntrenadorSesion().getEspecialidad_Entrenador())));
        ctv_entrenador_sesion.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getEntrenadorSesion().getNombre()));
    }

    private void formatearColumnasTvListaEntranadores() {
        ctv_nombre_entrenador.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNombre()));
        ctv_especialidad_entredor.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getEspecialidad_Entrenador().toString()));
    }

    private void formatearColumnasTvListaSolicitudes() {
        ctv_nombreSeccion.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getMiembro().getNombre()));
        ctv_especialidadSesion.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getEspecialidades())));
    }

    public void verInformacion(ActionEvent actionEvent) {
        SesionesEntrenamiento sesion = tv_sesiones_creadas.getSelectionModel().getSelectedItem();

        dp_fecha.setChronology(sesion.getFecha().getChronology());
        tf_Descripcion.setText(sesion.getDescripcion());
        tf_duracion.setText(sesion.getDuracion());


    }

    public void volver_menu(ActionEvent actionEvent) {
        controladorPrincipal.navegar("Login.fxml","menu");
        controladorPrincipal.cerrarVentana(txt_entrenador);
    }

    @FXML
    void modificadorLenguaje(ActionEvent actionEvent) {
        Idiomas idioma = comoboBoxLenguajes.getSelectionModel().getSelectedItem();

        String selector;

        switch (idioma) {
            case INGLES -> selector = "US";
            case FRANCES -> selector = "FR";
            default -> selector = "ES";
        }

        modificarTexto(selector);
    }

    public void modificarTexto(String identificador) {
        ResourceBundle resourceBundle;

        String pathName = "Properties/PanelAdministrador/EtiquetasAdmi";

        try {
            if (identificador != null) {
                resourceBundle = ResourceBundle.getBundle(pathName, new Locale(identificador));

                ctv_nombreSeccion.setText(resourceBundle.getString("ctv_nombreSeccion"));
                ctv_especialidadSesion.setText(resourceBundle.getString("ctv_especialidadSesion"));
                ctv_id.setText(resourceBundle.getString("ctv_id"));
                labelFechaSesion.setText(resourceBundle.getString("labelFechaSesion"));
                labelFechaEntrenador.setText(resourceBundle.getString("labelFechaEntrenador"));
                labelFechaDuracion.setText(resourceBundle.getString("labelFechaDuracion"));
                labelEspecialidad.setText(resourceBundle.getString("labelEspecialidad"));
                labelDescripcionSolicitud.setText(resourceBundle.getString("labelDescripcionSolicitud"));
                buttonAsignar.setText(resourceBundle.getString("buttonAsignar"));
                modificarLenguaje.setText(resourceBundle.getString("modificarLenguaje"));
                comoboBoxLenguajes.setPromptText(resourceBundle.getString("comoboBoxLenguajes"));
                buttonAtras.setText(resourceBundle.getString("buttonAtras"));
                labelFecha.setText(resourceBundle.getString("labelFecha"));
                labelDescripcion.setText(resourceBundle.getString("labelDescripcion"));
                labelDuracion.setText(resourceBundle.getString("labelDuracion"));
                labelEspecialidades.setText(resourceBundle.getString("labelEspecialidad"));
                labelDificultad.setText(resourceBundle.getString("labelDificultad"));
                ctv_nombre_entrenador.setText(resourceBundle.getString("ctv_nombre_entrenador"));
                ctv_especialidad_entredor.setText(resourceBundle.getString("ctv_especialidad_entredor"));
                buttonCrear.setText(resourceBundle.getString("buttonCrear"));
                buttonEliminar.setText(resourceBundle.getString("buttonEliminar"));
                buttonActualizar.setText(resourceBundle.getString("buttonActualizar"));
                buttonLimpiarCampos.setText(resourceBundle.getString("buttonLimpiarCampos"));
                bt_VerInformacion.setText(resourceBundle.getString("bt_VerInformacion"));




            }else{
                controladorPrincipal.mostrarAlerta("No ha seleccionado un idoma", Alert.AlertType.INFORMATION);
            }

        }catch (Exception e){
            controladorPrincipal.guardarLog(e.getMessage(),Level.SEVERE);
        }


    }
}

