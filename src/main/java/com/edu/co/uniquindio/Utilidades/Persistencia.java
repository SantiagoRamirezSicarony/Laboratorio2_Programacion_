package com.edu.co.uniquindio.Utilidades;

import com.edu.co.uniquindio.Modelo.*;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    private static final String RUTA_SESIONES = "src/main/resources/RutasPersistencia/dataSesiones.dat";
    private static final String RUTA_ADMINISTRADORES = "src/main/resources/RutasPersistencia/dataAdmins.dat";
    private static final String RUTA_MIEMBROS = "src/main/resources/RutasPersistencia/dataMiembros.dat";
    private static final String RUTA_ENTRENADORES = "src/main/resources/RutasPersistencia/dataEntrenadores.XML";
    private static final String RUTA_SOLICITUDES = "src/main/resources/RutasPersistencia/dataSolicitudes.XML";
    /**
     * Metodo que permite serializar un objeto en un archivo en la ruta especificada
     * @param objeto Objeto a serializar
     * @throws IOException
     */
    private static void serializarObjeto(String ruta, Object objeto) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
        oos.writeObject(objeto);
        oos.close();
    }


    /**
     * Metodo que permite deserializar un objeto de un archivo en la ruta especificada
     * @return Objeto deserializado
     * @throws Exception
     */
    private static Object deserializarObjeto(String ruta) throws Exception{

        if(!new File(ruta).exists()){
            return null;
        }


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
        Object objeto = ois.readObject();
        ois.close();


        return objeto;
    }
    public static void guardarSolicitudesMiembros(ArrayList<Solicitud> listaSolicitudes)throws IOException{

        serializarObjeto(RUTA_SOLICITUDES, listaSolicitudes);

    }
    public static ArrayList<Solicitud> cargarSolicitudesMiembros() throws Exception{
        return (ArrayList<Solicitud>) deserializarObjeto(RUTA_SOLICITUDES);
    }

    public static void guardarAdministradores(ArrayList<Administrador> listaAdministradores) throws IOException {
        serializarObjeto(RUTA_ADMINISTRADORES, listaAdministradores);
    }


    public static ArrayList<Administrador> cargarAdministradoores() throws Exception {
        return (ArrayList<Administrador>) deserializarObjeto(RUTA_ADMINISTRADORES);
    }

    public static void guardarSesiones(ArrayList<SesionesEntrenamiento> listaSesiones) throws IOException {
        serializarObjeto(RUTA_SESIONES, listaSesiones);
    }


    public static ArrayList<SesionesEntrenamiento> cargarSesiones() throws Exception {
        return (ArrayList<SesionesEntrenamiento>) deserializarObjeto(RUTA_SESIONES);
    }

    public static void guardarEntrenador(ArrayList<Entrenador> listaEntrenadores) throws IOException {
        serializarObjeto(RUTA_ENTRENADORES, listaEntrenadores);
    }
    public static ArrayList<Entrenador> cargarEntrenadores() throws Exception {
        return (ArrayList<Entrenador>) deserializarObjeto(RUTA_ENTRENADORES);
    }

    public static void guardarMiembros(ArrayList<Miembro> listaMiembros) throws IOException {
        serializarObjeto(RUTA_MIEMBROS, listaMiembros);
    }
    public static ArrayList<Miembro> cargarMiembros() throws Exception {
        return (ArrayList<Miembro>) deserializarObjeto(RUTA_MIEMBROS);
    }

}
