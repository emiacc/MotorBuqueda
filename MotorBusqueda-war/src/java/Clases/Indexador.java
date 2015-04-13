/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datos.Datos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Emi
 */
public class Indexador {

    private final HashMap<String, Integer> map;
    private final String directorio;
    private StringTokenizer tokens;

    public Indexador(String dir) {
        this.map = new HashMap();
        this.directorio = dir;
    }

    @Override
    public String toString() {
        return map.toString();
    }
    
    public String indexar() {
        try {
            File dir = new File(directorio);
            if (!Datos.getInstance().consultarDocumento(dir.getName())) return "Documento ya procesado";
            if (dir.exists()) {
                try (BufferedReader info = new BufferedReader(new InputStreamReader(new FileInputStream(new File(directorio)), "ISO-8859-1"))) {
                    String linea = info.readLine();
                    while (linea != null) {
                        //Obtiene un token por cada palabra que encuentre
                        tokens = new StringTokenizer(linea);
                        //Mientras existan tokens
                        while (tokens.hasMoreTokens()) {
                            String palabra = tokens.nextToken().toUpperCase();
                             //Expresion regular para eliminar todos los caracteres y solo dejar letras
                            Pattern exp = Pattern.compile("[^ÑÁÉÍÓÚA-Z]");
                            Matcher match = exp.matcher(palabra);
                            if (match.find()) {
                                palabra = match.replaceAll("");
                            }
                            if(palabra.length()>0 && palabra.compareTo(" ")!=0){
                            //Carga datos en el HashMap
                            if (!map.containsKey(palabra)) {
                                map.put(palabra, 1);
                            } else {
                                int cantidad = map.get(palabra);
                                map.put(palabra, ++cantidad);
                            }}
                        }
                        linea = info.readLine();
                    }
                    if(Datos.getInstance().insertarTabla(map,dir.getName(),dir.getAbsolutePath())) return "Procesado Correctamente";
                }
            }
        } catch (IOException e) {
            return "Error al leer el Archivo";
        }
        return "Error al leer el Archivo";
    }
}
