/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author lomik
 */
public class UDPClient {
    
     private  DatagramSocket socket;
     private byte[] buffer;
     InetAddress servidor;
     int serverPort;
     
     
     public UDPClient(String direccionHost,int serverPort) throws SocketException, UnknownHostException{
            socket = new DatagramSocket();
            //socket.setSoTimeout(puerto);
            buffer = new byte[1024];
           servidor = InetAddress.getByName(direccionHost);
           this.serverPort=serverPort;
           
            
     }
     
     
     public  String send(String mensaje) throws IOException{ 
         String centinela="";
         byte[] m =mensaje.getBytes(); //args[0].getBytes();
         DatagramPacket request = new DatagramPacket(m, mensaje.length(), servidor, serverPort);
         socket.send(request);
         DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        centinela= new String(reply.getData());
        System.out.println("Reply: " +centinela);
        return centinela;
           
     }
     
     public  String send(Mensaje mensaje) throws IOException{ 
         String centinela="";
         
           
           ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
           ObjectOutputStream os = new ObjectOutputStream(outputStream);
           os.writeObject(mensaje);
           byte[] data = outputStream.toByteArray();
           DatagramPacket request = new DatagramPacket(data, data.length, servidor, serverPort);
         socket.send(request);
         DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        centinela= new String(reply.getData());
        System.out.println("Reply: " +centinela);
        return centinela;
           
     }
     
     private void close(){
         this.socket.close();
     }
    
}
