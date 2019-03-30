/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import java.net.DatagramSocket;

/**
 *
 * @author lomik
 */
public class UDPServer {
    
    DatagramSocket socket;
    byte[] buffer;
    
    
    public UDPServer(int puerto){
        buffer= new byte[1000];
        
    }
    
    
    
    
    
}
