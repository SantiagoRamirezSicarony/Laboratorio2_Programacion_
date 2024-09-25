package com.edu.co.uniquindio.Controlador.ControladoresSecundarios;
import com.edu.co.uniquindio.Controlador.ControladorPrincipal;
import com.edu.co.uniquindio.Modelo.SistemaDeportes;
import com.edu.co.uniquindio.Modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorRegistroMiembro implements Initializable {


        @FXML
        private Button ButonRegistroUsuario;

        @FXML
        private Button buttonRegresarVentana;

        @FXML
        private TextField txtRegistroCorreo;

        @FXML
        private TextField txtRegistroEdad;

        @FXML
        private TextField txtRegistroIdentificacion;

        @FXML
        private TextField txtRegistroNombre;

        @FXML
        void registrarUsuario(ActionEvent event) {

                try{
                        if( !(txtRegistroNombre.getText().isEmpty()||txtRegistroCorreo.getText().isEmpty() ||
                                txtRegistroEdad.getText().isEmpty() || txtRegistroIdentificacion.getText().isEmpty())) {
                                System.out.println("nivel 1");

                                Usuario.getInstancia().setUsuarioLogueado(controladorPrincipal.crearMiembro(txtRegistroIdentificacion.getText(), txtRegistroNombre.getText()
                                        , txtRegistroCorreo.getText(), Integer.parseInt(txtRegistroEdad.getText())));




                                System.out.println("nivel 2");

                                controladorPrincipal.navegar("PanelMiembro.fxml","Panel Miembro");
                                controladorPrincipal.cerrarVentana(txtRegistroCorreo);

                        }

                }catch (Exception e){
                        System.out.println(e.getMessage());
                        controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.INFORMATION);

                }

        }
        @FXML

        void regresarVentana(ActionEvent event) {
                usuario= new Usuario();
                controladorPrincipal.navegar("Login.fxml","Login");
                controladorPrincipal.cerrarVentana(txtRegistroCorreo);

        }

        private ControladorPrincipal controladorPrincipal;
        private SistemaDeportes sistemaDeportes;
        private Usuario usuario;


        @Override
        public void initialize(URL location, ResourceBundle resources) {

                controladorPrincipal = ControladorPrincipal.getInstance();
                sistemaDeportes = controladorPrincipal.getSistemaDeportes();


        }


    public void retroceder(ActionEvent actionEvent) {
            controladorPrincipal.navegar("Login.fxml","menu") ;
            controladorPrincipal.cerrarVentana(txtRegistroCorreo);

    }
}


