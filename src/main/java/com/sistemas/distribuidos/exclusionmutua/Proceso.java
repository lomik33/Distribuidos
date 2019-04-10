/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.UDPClient;

/**
 *
 * @author lomik
 */
public class Proceso {
    int numero;
    boolean isCoordinador;
    int puerto;
    String direccion;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean getIsCoordinador() {
        return isCoordinador;
    }

    public void setIsCoordinador(boolean isCoordinador) {
        this.isCoordinador = isCoordinador;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    

    public Proceso(int numero, boolean isCoordinador, int puerto) {
        this.numero = numero;
        this.isCoordinador = isCoordinador;
        this.puerto = puerto;
        direccion = UDPClient.direcciones.get(numero);
    }

    /***
     * Constructor marca el proceso 1 como coordinador
     * @param numero
     * @param puerto 
     */
    public Proceso(int numero, int puerto) {
        this(numero,numero==1,puerto);       
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
     
    
}
