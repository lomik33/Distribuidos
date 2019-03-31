/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author lomik
 */
public class Mensaje implements Serializable {
    
    private int numeroMensaje;
    private int k;  
    private int tk;   
    private String datos;  
    private  ArrayList<Hm> Hmi;
    private boolean enEspera;

    public Mensaje() {
        Hmi= new ArrayList();
    }

    public Mensaje(int numeroMensaje, int k, int tk, String datos, ArrayList Hmi, boolean enEspera) {
        super();
        this.numeroMensaje = numeroMensaje;
        this.k = k;
        this.tk = tk;
        this.datos = datos;
        this.Hmi = Hmi;
        this.enEspera = enEspera;
    }

    public Mensaje(int numeroMensaje, int k, int tk, String datos) {
           super();
        this.numeroMensaje = numeroMensaje;
        this.k = k;
        this.tk = tk;
        this.datos = datos;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public String toString() {
        String strhmi="{";
        for(Hm hmi:Hmi){
            strhmi+="("+hmi.getL()+","+hmi.getTl()+')';
        }
        strhmi+=')';
            
        return "m" +numeroMensaje +"(" +   ", k=" + k + ", tk=" + tk + ", datos=" + datos + ", Hmi=" + strhmi + '}';
    }
    
    

    
}
