/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lomik
 */
public class UDPServer {
    
  private  DatagramSocket socket;
  private byte[] buffer;
    
    
    public UDPServer(int puerto) throws SocketException{
        buffer= new byte[1000];
        socket= new DatagramSocket(puerto);        
    }
    
    
    public void listen(){
        
          while (true) {
              try {
                  System.out.println("Escuchando en puerto:"+socket.getPort());
                  DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                  socket.receive(request);
                  System.out.println(request);
                  DatagramPacket reply = new DatagramPacket(request.getData(),
                          request.getLength(), request.getAddress(), request.getPort());
                  socket.send(reply);
              } catch (IOException ex) {
                  Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
    }
    
    
    
}
