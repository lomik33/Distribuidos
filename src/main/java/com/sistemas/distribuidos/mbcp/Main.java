/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import java.net.SocketException;

/**
 *
 * @author lomik
 */
public class Main {
    
    public static void main(String[] args) throws SocketException{
      
        UDPServer server= new UDPServer(6789);
        server.listen();
               
    }
}
