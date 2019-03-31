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
        int numeroMensaje=0;
          while (true) {
              try {
                  System.out.println("Escuchando en puerto:"+socket.getPort());
                  numeroMensaje++;
                  DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                  socket.receive(request);
                  System.out.println(request);
                  
                     String respuesta= new String(request.getData());
                     respuesta="Respuesta: "+numeroMensaje+" "+respuesta;
                   byte[]  respuestaBytes=respuesta.getBytes();
                  DatagramPacket reply = new DatagramPacket(respuestaBytes,
                          respuestaBytes.length, request.getAddress(), request.getPort());
                  socket.send(reply);
              } catch (IOException ex) {
                  Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
    }
    
    
    
}
