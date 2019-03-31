/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

import java.util.HashMap;

/**
 *
 * @author lomik
 */
public class MBCPAlgoritmo {
    
    private int [] VTp;
    private int numeroProceso;
    HashMap<Integer, Integer> CI ;
    
   public  MBCPAlgoritmo(){
       VTp= new int[6];
       numeroProceso=1;
       CI= new HashMap<Integer, Integer>();
       
   }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void limpiar(){
        for(int i=0;i<VTp.length;i++){
            VTp[i]=0;
        }
    }
}
