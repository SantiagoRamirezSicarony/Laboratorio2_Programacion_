package com.edu.co.uniquindio.Utilidades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.*;

import static java.nio.file.Files.exists;

public class LoggerSistem {
    public static final Logger logger = Logger.getLogger(LoggerSistem.class.getName());

    public static void log(String mensajeLog, Level nivel, String accion){
        guardarRegistroLog(mensajeLog,nivel,accion,"src/main/resources/MyLog/Mylog.txt");
    }

    public static void guardarRegistroLog(String mensajeLog, Level nivel, String accion, String rutaArchivo) {
        String log = "";
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler = null;
        cargarFechaSistema();
        try {
            fileHandler = new FileHandler(rutaArchivo, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.log(nivel,accion + "," + mensajeLog + "," + fechaSistema);

        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } finally {

            fileHandler.close();
        }

    }

    static String fechaSistema;

    private static void cargarFechaSistema() {

        String diaN = "";
        String mesN = "";
        String añoN = "";

        Calendar cal1 = Calendar.getInstance();


        int dia = cal1.get(Calendar.DATE);
        int mes = cal1.get(Calendar.MONTH) + 1;
        int año = cal1.get(Calendar.YEAR);
        int hora = cal1.get(Calendar.HOUR);
        int minuto = cal1.get(Calendar.MINUTE);


        if (dia < 10) {
            diaN += "0" + dia;
        } else {
            diaN += "" + dia;
        }
        if (mes < 10) {
            mesN += "0" + mes;
        } else {
            mesN += "" + mes;
        }


        fechaSistema = año + "-" + mesN + "-" + diaN;

    }

}
