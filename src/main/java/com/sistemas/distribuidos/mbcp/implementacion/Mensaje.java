/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author ismael
 */
public class Mensaje implements Serializable {

    //1=MBCP, 2,MUTEX, 3 ANILLO
    public int TIPO_MENSAJE;
    
    
    private int numeroMensaje;
    private int k;
    private int tk;
    private String datos;
    private CopyOnWriteArrayList<Hm> Hmi;
    
    private boolean enEspera;    
    private String direccion; 
    //Variables para mensajes del algoritmo de exclusion mutua
    private double saldoRegionCriticaGinna;
    private double salgoRegionCriticaIsmael;


    public Mensaje() {
        Hmi = new CopyOnWriteArrayList();
    }


    public Mensaje(int numeroMensaje, int k, int tk, String datos, CopyOnWriteArrayList<Hm> ci) {
        this();
        this.numeroMensaje = numeroMensaje;
        this.k = k;
        this.tk = tk;
        this.datos = datos;
        this.Hmi.addAll(ci);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public String toString() {
        String strhmi = "";
        if (Hmi != null) {
            strhmi = "{";
            for (Hm hmi : Hmi) {
                strhmi += "(" + hmi.getL() + "," + hmi.getTl() + ')';
            }
            strhmi += ')';
        } else {
            strhmi = "{\u2205}";
        }

        return "m" + numeroMensaje + "(" + "k=" + k + ", tk=" + tk + ", datos=\"" + datos + "\", Hmi=" + strhmi + ')';
    }

    public int getNumeroMensaje() {
        return numeroMensaje;
    }

    public void setNumeroMensaje(int numeroMensaje) {
        this.numeroMensaje = numeroMensaje;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getTk() {
        return tk;
    }

    public void setTk(int tk) {
        this.tk = tk;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public CopyOnWriteArrayList<Hm> getHmi() {
        return Hmi;
    }

    public void setHmi(CopyOnWriteArrayList<Hm> Hmi) {
        this.Hmi = Hmi;
    }

    public boolean isEnEspera() {
        return enEspera;
    }

    public void setEnEspera(boolean enEspera) {
        this.enEspera = enEspera;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSaldoRegionCriticaGinna() {
        return saldoRegionCriticaGinna;
    }

    public void setSaldoRegionCriticaGinna(double saldoRegionCriticaGinna) {
        this.saldoRegionCriticaGinna = saldoRegionCriticaGinna;
    }

    public double getSalgoRegionCriticaIsmael() {
        return salgoRegionCriticaIsmael;
    }

    public void setSalgoRegionCriticaIsmael(double salgoRegionCriticaIsmael) {
        this.salgoRegionCriticaIsmael = salgoRegionCriticaIsmael;
    }

    
    
}
