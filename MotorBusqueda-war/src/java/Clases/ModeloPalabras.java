/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Emi
 */
public class ModeloPalabras extends AbstractTableModel{
    private final List<Palabra> lista;

    public ModeloPalabras(List<Palabra> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return lista.get(rowIndex).getId();
            case 1: return lista.get(rowIndex).getPalabra();
            case 2: return lista.get(rowIndex).getContador();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Id";
            case 1: return "Palabra";
            case 2: return "Contador";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Integer.class;
        }
        return null;
    }  
    
    public void removeRow(int i){
        lista.remove(i);
    }    

    public void removeAllRows() {
        lista.clear();
    }
}
