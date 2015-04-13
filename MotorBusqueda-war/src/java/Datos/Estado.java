/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emi
 */
public class Estado {
    private static final String ruta = "C:\\Users\\Emi\\Documents\\NetBeansProjects\\MotorBusqueda\\resultado.txt";
    public static void guardar(String resultado) {
        try {
            File archivo = new File(ruta);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(resultado);
            } catch (IOException ex) {
                Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String leer(){
        String rta = "";
        try{
            FileInputStream fstream = new FileInputStream(ruta);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
                rta = rta + strLinea;
            }
            entrada.close();
        }catch (Exception e){ 
            System.err.println("Ocurrio un error: " + e.getMessage());
        }        
        return rta;
    }

}
