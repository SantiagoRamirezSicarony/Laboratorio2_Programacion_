package com.edu.co.uniquindio.Utilidades;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;

public class MetodosEscribir {

    static String  rutaDirectorio= "C://Reportes_Java";
    public static void escribir(ArrayList<?> lista, ArrayList<?> list2) throws IOException {
        File fh = new File(rutaDirectorio);
        if (fh.exists()&&fh.isDirectory()){
            File ff = new File(rutaDirectorio+"/lista1.txt");
            File ff2 = new File(rutaDirectorio+"/lista2.txt");
            escribirLista(ff,lista);
            escribirLista(ff2,list2);


        }else {
            fh.mkdir();
            escribir(lista, list2);
        }


    }

    private static void escribirLista(File ff, ArrayList<?> list) throws IOException {
        FileWriter fw = new FileWriter(ff);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < list.size(); i++) {
            bw.write(list.get(i).toString());
            bw.flush();
            if(i==9){
                bw.flush();
                break;
            }else {
                bw.write(list.get(i).toString());
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> lista1 = new ArrayList<>(Arrays.asList("Hola","Hola","Hola","Hola","Hola","Hola","Hola"));
        ArrayList<String> lista2 = new ArrayList<>(Arrays.asList("Buenas","Buenas","Buenas","Buenas","Buenas","Buenas","Buenas"));
        try{
            escribir(lista1,lista2);
        }catch(Exception e){

        }
    }
}

