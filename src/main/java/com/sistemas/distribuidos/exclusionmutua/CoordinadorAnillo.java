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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lomik
 */
public class CoordinadorAnillo implements MensajeListen {
    public static List<Proceso> procesos;
    public static Proceso procesoActual;
    
    static{
        procesos= new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean recibirMensaje(Mensaje mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void actualizaEstatus(Proceso proceso){
        
        for(Proceso p: procesos){
            if(p.getNumero()==proceso.getNumero()){
                p.setEstatus(proceso.getEstatus()); 
                p.isCoordinador=proceso.getIsCoordinador();
            }
               
            
        }
    }
    
    
     public static void actualizaEstatus(int numero, String estatus, boolean isCoordinador){
        
        for(Proceso p: procesos){
            if(p.getNumero()==numero){
                  p.setEstatus(estatus);
                  p.isCoordinador=isCoordinador;
            }              
        }
      
    }
     
     public static Proceso getProceso(int numero){
          for(Proceso p: procesos){
            if(p.getNumero()==numero)
                return p;
        }
          return null;
     }
     
     public static Proceso getSiguienteProceso(Proceso proceso){
         
         int siguiente=proceso.numero;
         switch(proceso.numero){            
             case 6:
                 
//                 Proceso primero=CoordinadorAnillo.getProceso(1);
//                 if(!isALive(primero,null))                
//                    siguiente=2;
//                 else
                     siguiente=1;
                 
                 
                 
                 break;
             default:
                 siguiente++;
                 break;                     
         }
         Proceso procesoSiguiente=CoordinadorAnillo.getProceso(siguiente);
//         boolean isAlive=isALive(procesoSiguiente,null);
//         if(isAlive)
//             return procesoSiguiente;         
//         else
//             return getSiguienteProceso(procesoSiguiente);
return procesoSiguiente;

        
     }
     
     
     public static boolean isALive(Proceso proceso,Mensaje mensaje){
         boolean centinela=false;
         String response = "";
           try {
               if(mensaje==null){
                   mensaje=new Mensaje();
                   mensaje.setDatos("isAlive");
                   mensaje.setK(proceso.getNumero());
               }
            UDPClient ping = new UDPClient(proceso.getDireccion(), proceso.puerto, 5000);
            response = ping.send(mensaje);
            if(!response.equals(""))
                centinela=true;
        } catch (SocketException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        }
       return centinela;
     }
    
   
    
}
