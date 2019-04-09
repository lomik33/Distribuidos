/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.util.ArrayList;

/**
 *
 * @author lomik
 */
public class CentralizadoMutex implements MensajeListen {
    
    
        public ArrayList<Mensaje> mensajesRecibidos;


    @Override
    public boolean agregarMensaje(Mensaje mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
