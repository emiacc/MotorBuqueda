/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Clases.Palabra;
import Clases.Posteo;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emi
 */
public class Datos {

    public static Datos instance = new Datos();
    private String ruta = "jdbc:sqlite:C:\\Users\\Emi\\Documents\\NetBeansProjects\\MotorBusqueda\\DBVocabulario2.s3db";
    private Datos() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Error " + ex.getMessage());
        }
    }

    public static Datos getInstance() {
        return instance;
    }
    public List getDocumentos(){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select nombre from Documentos;");
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }          
            return lista;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public List getDocumentos(int id){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select d.nombre from Documentos d, PalabrasDocumentos p where d.id = p.idDocumento and p.idPalabra = "+id+";");
            ArrayList<String> lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }          
            return lista;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public boolean consultarDocumento(String archivo) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select id from Documentos where nombre = '" + archivo + "';");
            if (rs.next()) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }

    public boolean insertarTabla(HashMap map, String archivo, String path) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        int idPalabra,idArchivo;
        try {
            con = DriverManager.getConnection(ruta);
            con.setAutoCommit(false);
            stat = con.createStatement();

            //Guardar Documento
            stat.executeUpdate("insert into Documentos (nombre, directorio) values ('" + archivo + "','" + path + "');");
            rs = stat.executeQuery("SELECT last_insert_rowid();");
            idArchivo = rs.getInt(1);
            //Guardar Palabras
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                rs = stat.executeQuery("select id,contador from Palabras where palabra = '" + e.getKey() + "';");
                if (rs.next()) {
                    //update
                    idPalabra = rs.getInt(1);
                    int cont = rs.getInt(2) + (int) e.getValue();
                    stat.executeUpdate("update Palabras set contador = " + cont + " where palabra = '" + e.getKey() + "';");
                } else {
                    //insert
                    stat.executeUpdate("insert into Palabras (palabra,contador) values ('" + e.getKey() + "'," + e.getValue() + ");");
                    rs = stat.executeQuery("SELECT last_insert_rowid();");
                    idPalabra = rs.getInt(1);
                }
                stat.executeUpdate("insert into PalabrasDocumentos values ("+idPalabra+","+idArchivo+","+(int) e.getValue()+");");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }        
    }
    
    public List<Palabra> getListado(String c){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        List<Palabra> lista = new ArrayList<>();
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select id,palabra, contador from Palabras where palabra LIKE '"+c+"%';");
            while(rs.next()){
                 lista.add(new Palabra(rs.getInt(1),rs.getString(2),rs.getInt(3)));                            
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }        
    }  
    
    public List<Posteo> getListaPosteo(String palabra){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        List<Posteo> lista = new ArrayList<>();
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select id from Palabras where palabra LIKE '"+palabra+"';");
            if(rs.next()){
                int id = rs.getInt(1);
                rs = stat.executeQuery("select idDocumento, cantidad from PalabrasDocumentos where idPalabra = "+id+" order by cantidad desc;");
                while(rs.next()){
                    lista.add(new Posteo(rs.getInt(1),rs.getInt(2)));
                }
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public int getCantidadDocumentos(){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select COUNT(*) from Documentos;");
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return 0;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public int getNr(String palabra){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select id from Palabras where palabra LIKE '"+palabra+"';");
            if(rs.next()){
                int id = rs.getInt(1);
                rs = stat.executeQuery("select COUNT(*) from PalabrasDocumentos where idPalabra="+id+";");
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return 0;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }
    }
    
    public String getUbicacion(int id){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ruta);
            stat = con.createStatement();
            rs = stat.executeQuery("select nombre, directorio from Documentos where id="+id+";");
            if (rs.next()) {
                return "<a href='"+rs.getString(2)+"'>"+rs.getString(1)+"</a>";
            }
            return "";
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return "";
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (stat != null) try { stat.close(); } catch (SQLException e) { }
            if (con != null) try {  con.commit(); con.close(); } catch (SQLException e) { }
        }        
    }
}
