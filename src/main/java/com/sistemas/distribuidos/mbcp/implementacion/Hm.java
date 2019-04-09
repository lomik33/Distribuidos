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
 * @author lomik
 */
public class Hm implements Serializable {
    
    private  int l;
    
    private int tl;

    public Hm(int l, int tl) {
        this.l = l;
        this.tl = tl;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }
    
   public static String toStringHm(CopyOnWriteArrayList<Hm> Hmi){
        String strhmi = "";
        if (Hmi != null) {
            strhmi = "{";
            for (Hm hmi : Hmi) {
                strhmi += "(" + hmi.getL() + "," + hmi.getTl() + ')';
            }
            strhmi += ')';
        } else {
            strhmi = "{(vacio)}";
        }
        
        return strhmi;
   }
}
