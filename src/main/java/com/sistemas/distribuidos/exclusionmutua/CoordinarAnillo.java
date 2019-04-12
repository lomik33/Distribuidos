/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author lomik
 */
public class CoordinarAnillo implements MensajeListen {
    public static List<Proceso> procesos;
      public static Proceso procesoActual;
    
    static{
        procesos= new CopyOnWriteArrayList<Proceso>();
    }

    @Override
    public boolean recibirMensaje(Mensaje mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   
    
}
