/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.UDPClient;

/**
 *
 * @author Ginna Monserrat
 */
public class Proceso {
    int numero;
    boolean isCoordinador;
    int puerto;
    String direccion;
    boolean isDead;

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
        this.estatus="NO PARTICIPANTE";
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
    
    //Participante no participante
     private String estatus;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return String.format("PROCESO %d %s %s", this.numero,(this.isCoordinador?"COORDINADOR":" "),this.estatus);
    }

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
     
    
    
     
    
}
