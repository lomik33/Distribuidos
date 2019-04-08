/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author lomik
 */
public class Mensaje implements Serializable {

    private int numeroMensaje;
    private int k;
    private int tk;
    private String datos;
    private CopyOnWriteArrayList<Hm> Hmi;
    private boolean enEspera;

    public Mensaje() {
        Hmi = new CopyOnWriteArrayList();
    }


    public Mensaje(int numeroMensaje, int k, int tk, String datos, CopyOnWriteArrayList<Hm> ci) {
        this();
        this.numeroMensaje = numeroMensaje;
        this.k = k;
        this.tk = tk;
        this.datos = datos;
        this.Hmi=ci;
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

}
