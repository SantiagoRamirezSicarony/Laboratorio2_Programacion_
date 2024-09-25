module Laboratorio1.Programacion.SantiagoRamirezSicarony {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;
    requires java.logging;


    opens com.edu.co.uniquindio.Modelo to javafx.fxml;
    exports com.edu.co.uniquindio.Modelo;
    opens com.edu.co.uniquindio.Controlador to javafx.fxml;
    exports com.edu.co.uniquindio.Controlador;
    opens com.edu.co.uniquindio.Controlador.ControladoresSecundarios to javafx.fxml;
    exports com.edu.co.uniquindio.Controlador.ControladoresSecundarios;
    exports com.edu.co.uniquindio.Modelo.Enumeraciones;
    opens com.edu.co.uniquindio.Modelo.Enumeraciones;
    exports com.edu.co.uniquindio.app;
    opens com.edu.co.uniquindio.app to javafx.fxml, javafx.graphics;
}