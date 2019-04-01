/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lomik
 */
public class UDPServer implements Runnable {

    private DatagramSocket socket;
    private byte[] buffer;
    MensajeListen listener;

    private ArrayList<Mensaje> mensajesRecibidos;
    public UDPServer(int puerto, ArrayList<Mensaje> mensajesRecibidos, MensajeListen mensajelistener) throws SocketException {
        buffer = new byte[1024];
        socket = new DatagramSocket(puerto);
        System.out.println("Escuchando en puerto:" + puerto);
        this.mensajesRecibidos=mensajesRecibidos;
        listener=mensajelistener;
    }

    public void listen() {
        int numeroMensaje = 0;
        while (true) {
            try {

                numeroMensaje++;
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                byte[] data = request.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                Mensaje mensaje = null;
                try {
                    mensaje = (Mensaje) is.readObject();
                    System.out.println(mensaje + " de: " + request.getAddress() + ":" + request.getPort());
                    this.mensajesRecibidos.add(mensaje);
                    listener.agregarMensaje(mensaje);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                String respuesta = mensaje.toString();
                byte[] respuestaBytes = respuesta.getBytes();

                DatagramPacket reply = new DatagramPacket(respuestaBytes,
                        respuestaBytes.length, request.getAddress(), request.getPort());
                socket.send(reply);
            } catch (IOException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        listen();

    }

}
