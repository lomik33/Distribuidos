package com.sistemas.distribuidos.exclusionmutua;


import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.JList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ginna
 */
public  class UtilsAlgoritmos {
    
    
    /***
     * Metódo utilitario que permite refrescar el contenido de un componente JList con base a una lista de objetos Mensaje
     * @param list
     * @param mensajes 
     */
    public static void actualizaLista(JList list, List<Mensaje> mensajes) {
        String[] esperaStr = new String[mensajes.size()];
        int i = 0;
        for (Mensaje m : mensajes) {
            esperaStr[i] = m.getDatos();
            i++;
        }  
        list.setListData((String[]) esperaStr);
          
    }
    
    
        /** * Metódo utilitario que permite refrescar el contenido de un componente JList con base a una lista de objetos Mensaje
     * @param list
     * @param procesos
     * @param mensajes 
     */
    public static void actualizaListaProcesos(JList list, List<Proceso> procesos) {
        String[] esperaStr = new String[procesos.size()];
        int i = 0;
        for (Proceso m : procesos) {
            esperaStr[i] = m.toString();
            i++;
        }  
        list.setListData((String[]) esperaStr);
          
    }
    
   
}
