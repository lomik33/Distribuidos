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
import java.util.HashMap;

/**
 *
 * @author lomik
 */
public class UDPClient {
    
     private  DatagramSocket socket;
     private byte[] buffer;
     private InetAddress servidor;
     int serverPort;
     
     
     public UDPClient(String direccionHost,int serverPort) throws SocketException, UnknownHostException{
            socket = new DatagramSocket();
            //socket.setSoTimeout(puerto);
            buffer = new byte[1024];
           servidor = InetAddress.getByName(direccionHost);
           this.serverPort=serverPort;
           
            
     }
     
        public UDPClient(String direccionHost,int serverPort, int timeout) throws SocketException, UnknownHostException{
            socket = new DatagramSocket();
            socket.setSoTimeout(timeout);
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

    public DatagramSocket getSocket() {
        return socket;
    }
    
      public static HashMap<Integer, String> direcciones;
      
     static{
          direcciones = new HashMap();
     
            direcciones.put(1, "192.168.19.140");
            direcciones.put(2, "192.168.19.140");
            direcciones.put(3, "192.168.19.133");
            direcciones.put(4, "192.168.19.133");
            direcciones.put(5, "192.168.16.173");
            direcciones.put(6, "192.168.16.173");
        
//                direcciones.put(1, "192.168.1.17");
//                direcciones.put(2, "192.168.1.17");
//                direcciones.put(3, "192.168.1.17");
//                direcciones.put(4, "192.168.1.12");
//                direcciones.put(5, "192.168.1.12");
//                direcciones.put(6, "192.168.1.12");
       

     }
     
}
