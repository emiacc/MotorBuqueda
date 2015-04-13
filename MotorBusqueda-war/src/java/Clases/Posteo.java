/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Emiliano
 */
public class Posteo {
    private int idDocuemnto;
    private int cantidad;

    public Posteo(int idDocuemnto, int cantidad) {
        this.idDocuemnto = idDocuemnto;
        this.cantidad = cantidad;
    }

    public int getIdDocuemnto() {
        return idDocuemnto;
    }

    public void setIdDocuemnto(int idDocuemnto) {
        this.idDocuemnto = idDocuemnto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }    
}
