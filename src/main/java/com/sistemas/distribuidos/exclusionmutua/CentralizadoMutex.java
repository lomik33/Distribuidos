/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.UDPClient;

import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

/**
 *
 * @author lomik
 */
public class CentralizadoMutex implements MensajeListen {
    
    ArrayList<Proceso> procesos;
    public ArrayList<Mensaje> mensajesRecibidos;
    Proceso coordinador;
    Proceso proceso;
    
    private JList listColaPeticionesGinna;
    private JList listColaPeticionesIsmael;
            
   private CentralizadoMutex(){
                procesos= new ArrayList();
                this.mensajesRecibidos= new ArrayList();
            }
   
   public CentralizadoMutex(Proceso proceso, JList listColaPeticionesGinna, JList listColaPeticionesIsmael){
               this();
               if(proceso.isCoordinador)
                   this.coordinador=proceso;
               else{
                   this.coordinador= new Proceso(1,FrameCentralizado.puertoInicial+1);
                   this.coordinador.direccion=UDPClient.direcciones.get(1);
               }
               this.proceso=proceso;
               this.listColaPeticionesGinna=listColaPeticionesGinna;
               this.listColaPeticionesIsmael=listColaPeticionesIsmael;
            }
   
    @Override
    public boolean recibirMensaje(Mensaje mensaje) {      
       boolean centinela=false;
       this.mensajesRecibidos.add(mensaje);
       this.actualizaLista(listColaPeticionesGinna, mensajesRecibidos);
       System.out.println(mensaje);
       return centinela;
    }
    
    
    public boolean enviarMensaje(int regionCritica){
        boolean centinela=false;
          Mensaje mensaje= new Mensaje();
        mensaje.setK(this.proceso.numero);
        mensaje.setTk(1);
        mensaje.setDatos(String.format("proceso:%s desea retirar $1.0", Integer.toString(this.proceso.numero)));
        try {
            UDPClient client = new UDPClient(this.coordinador.direccion, this.coordinador.getPuerto());
            String respuesta = client.send(mensaje);
            if (respuesta != null) {
                System.out.println(respuesta);
            }
            centinela= true;
        } catch (SocketException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return centinela;
    }
    
    /***
     * Metódo utilitario que permite refrescar el contenido de un componente JList con base a una lista de objetos Mensaje
     * @param list
     * @param mensajes 
     */
    private void actualizaLista(JList list, List<Mensaje> mensajes) {
        String[] esperaStr = new String[mensajes.size()];
        int i = 0;
        for (Mensaje m : mensajes) {
            esperaStr[i] = m.toString();
            i++;
        }
        list.setListData((String[]) esperaStr);
    }
    
}
