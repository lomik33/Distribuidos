/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

/**
 *
 * @author lomik
 */
public interface MensajeListen {
    /**
     * primitiva receive que permite capturar mensajes que vienen desde los procesos en el sistema distribuido
     * @param mensaje
     * @return 
     */
    public boolean recibirMensaje(Mensaje mensaje);
}
