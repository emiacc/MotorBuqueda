/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Datos.Datos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Emiliano
 */
public class Buscador {

    private HashMap<Integer, Double> ranking;
    private StringTokenizer tokens;
    private String cadena;
    
    public Buscador(String cadena) {
        this.cadena = cadena;        
    }
    
    public String buscar(){
        ranking = new HashMap();
        Integer N = Datos.getInstance().getCantidadDocumentos();
        LinkedList<String> palabrasRecuperadas = new LinkedList();
        ArrayList<Posteo> listaPosteo = new ArrayList();
        tokens = new StringTokenizer(cadena);
        //Mientras existan tokens
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken().toUpperCase();
            //Expresion regular para eliminar todos los caracteres y solo dejar letras
            Pattern exp = Pattern.compile("[^ÑÁÉÍÓÚA-Z]");
            Matcher match = exp.matcher(palabra);
            if (match.find()) {
                palabra = match.replaceAll("");
            }
            if (palabra.length() > 0 && palabra.compareTo(" ") != 0) {
                palabrasRecuperadas.add(palabra);
            }
        }
        
        if (palabrasRecuperadas.size() != 0) {
            for (String palabra : palabrasRecuperadas) {
                listaPosteo = (ArrayList<Posteo>) Datos.getInstance().getListaPosteo(palabra);
                int nr =  Datos.getInstance().getNr(palabra);
                for(Posteo p : listaPosteo){
                    if(ranking.containsKey(p.getIdDocuemnto())){
                        double valorRanking = ranking.get(p.getIdDocuemnto());
                        valorRanking += 1 + p.getCantidad()* Math.log10(((double)N /(double) nr));
                        this.ranking.put(p.getIdDocuemnto(), valorRanking);
                    }
                    else{
                        double log = Math.log10(((double)N /(double) nr));
                        double valorRanking = p.getCantidad()* log;  
                        ranking.put(p.getIdDocuemnto(), valorRanking);
                    }
                }
            }
        }
        Map<Integer, Double> sortedMap = sortByComparator(ranking);
        return sortedMap.toString();
    }
    
    
    private static Map<Integer, Double> sortByComparator(Map<Integer, Double> unsortMap) {
 
		// Convert Map to List
		List<Map.Entry<Integer, Double>> list = 
			new LinkedList<Map.Entry<Integer, Double>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
			public int compare(Map.Entry<Integer, Double> o1,
                                           Map.Entry<Integer, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
		for (Iterator<Map.Entry<Integer, Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Integer, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}