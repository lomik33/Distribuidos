/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.exclusionmutua;

import com.sistemas.distribuidos.mbcp.UDPClient;
import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author lomik
 */
public class CentralizadoMutex implements MensajeListen {

    public ArrayList<Mensaje> mensajesRecibidos;
    private CopyOnWriteArrayList<Mensaje> colaPeticionesRegionCriticaGinna;
    private CopyOnWriteArrayList<Mensaje> colaPeticionesRegionCriticaIsmael;
    Proceso coordinador;
    Proceso proceso;

    public static boolean regionCriticaGinnaEnUso;
    public static boolean regionCriticaIsmaelEnUso;
    public static double saldoGinna;
    public static double saldoIsmael;
    private JList listColaPeticionesGinna;
    private JList listColaPeticionesIsmael;
    private JLabel lblBloqueoCtaGinna;
    private JLabel lblBloqueoCtaIsmael;
    private JLabel lblSaldoGinna;
    private JLabel lblSaldoIsmael;

    private JButton btnRegionCriticaGinna;
    private JButton btnRegionCriticaIsmael;

    private JList listProcesosActivos;

    private CentralizadoMutex() {

        this.mensajesRecibidos = new ArrayList();
        this.colaPeticionesRegionCriticaGinna = new CopyOnWriteArrayList<Mensaje>();
        this.colaPeticionesRegionCriticaIsmael = new CopyOnWriteArrayList<Mensaje>();
        saldoGinna = 2000;
        saldoIsmael = 1500;

    }

    public CentralizadoMutex(Proceso proceso, JList listColaPeticionesGinna, JList listColaPeticionesIsmael, JLabel lblEstatusCuentaGinna, JLabel lblBloqueoCtaIsmael, JButton btnRegionCriticaGinna, JButton btnRegionCriticaIsmael, JLabel lblSaldoGinna, JLabel lblSaldoIsmael, JList listProcesosActivos) {
        this();
        if (proceso.isCoordinador) {
            this.coordinador = proceso;
        } else {
            this.coordinador = new Proceso(1, FrameCentralizado.puertoInicial + 1);
            this.coordinador.direccion = UDPClient.direcciones.get(1);
        }
        this.proceso = proceso;
        this.listColaPeticionesGinna = listColaPeticionesGinna;
        this.listColaPeticionesIsmael = listColaPeticionesIsmael;
        this.lblBloqueoCtaGinna = lblEstatusCuentaGinna;
        this.lblBloqueoCtaIsmael = lblBloqueoCtaIsmael;
        this.btnRegionCriticaGinna = btnRegionCriticaGinna;
        this.btnRegionCriticaIsmael = btnRegionCriticaIsmael;
        this.lblSaldoGinna = lblSaldoGinna;
        this.lblSaldoIsmael = lblSaldoIsmael;
        this.listProcesosActivos = listProcesosActivos;
    }

    @Override
    public boolean recibirMensaje(Mensaje mensaje) {
        boolean centinela = false;
        //Algortitmo anillo
        if (mensaje.TIPO_MENSAJE == 3) {
            this.recepcionAlgoritmoAnillo(mensaje);
            return true;
        }

        if (proceso.isCoordinador) {

            SwingUtilities.invokeLater(() -> {
                if (mensaje.getTk() == 1) {
                    if (mensaje.getSaldoRegionCriticaGinna() == 0) {
                        try {
                            this.colaPeticionesRegionCriticaGinna.add(mensaje);
                            UtilsAlgoritmos.actualizaLista(listColaPeticionesGinna, this.colaPeticionesRegionCriticaGinna);
                            this.recibirMensajeRegionCriticaGinna(mensaje);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (mensaje.getTk() == 2) {

                    this.colaPeticionesRegionCriticaIsmael.add(mensaje);
                    UtilsAlgoritmos.actualizaLista(listColaPeticionesIsmael, this.colaPeticionesRegionCriticaIsmael);
                    this.recibirMensajesRegionCriticaIsmael(mensaje);
                }
            });

        } else {
            if (mensaje.getTk() == 1) {

                this.lblSaldoGinna.setText(String.format("Saldo: $%f", mensaje.getSaldoRegionCriticaGinna()));
                this.btnRegionCriticaGinna.setEnabled(true);
            } else if (mensaje.getTk() == 2) {
                this.lblSaldoIsmael.setText(String.format("Saldo: $%f", mensaje.getSalgoRegionCriticaIsmael()));
                this.btnRegionCriticaIsmael.setEnabled(true);
            }

        }

        return centinela;

    }

    private synchronized void recibirMensajeRegionCriticaGinna(Mensaje mensaje) throws InterruptedException {

        //En el mensaje tk seria el recurso que se desea obtener
        //Region critica cuenta de Ginna
        if (regionCriticaGinnaEnUso) {
            //this.colaPeticionesRegionCriticaGinna.add(mensaje);
            UtilsAlgoritmos.actualizaLista(listColaPeticionesGinna, colaPeticionesRegionCriticaGinna);
        } else {
            regionCriticaGinnaEnUso = true;
            lblBloqueoCtaGinna.setEnabled(false);
            btnRegionCriticaGinna.setEnabled(false);
            lblBloqueoCtaGinna.setText("EN USO POR EL PROCESO:" + Integer.toString(mensaje.getK()));

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    lblBloqueoCtaGinna.setEnabled(true);
                    btnRegionCriticaGinna.setEnabled(true);
                    lblBloqueoCtaGinna.setText("LIBERADO");
                    regionCriticaGinnaEnUso = false;
                    saldoGinna--;
                    lblSaldoGinna.setText(String.format("Saldo: $%f", saldoGinna));
                    try {
                        enviarRespuesta(mensaje);
                    } catch (IOException ex) {
                        Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    colaPeticionesRegionCriticaGinna.remove(mensaje);
                    UtilsAlgoritmos.actualizaLista(listColaPeticionesGinna, colaPeticionesRegionCriticaGinna);
                    for (Mensaje m : colaPeticionesRegionCriticaGinna) {
                        try {
                            recibirMensajeRegionCriticaGinna(m);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });

        }

    }

    private synchronized void recibirMensajesRegionCriticaIsmael(Mensaje mensaje) {

        //En el mensaje tk seria el recurso que se desea obtener
        //Region critica cuenta de Ginna
        if (regionCriticaIsmaelEnUso) {
            //this.colaPeticionesRegionCriticaGinna.add(mensaje);
            UtilsAlgoritmos.actualizaLista(listColaPeticionesGinna, colaPeticionesRegionCriticaIsmael);

        } else {
            regionCriticaIsmaelEnUso = true;
            lblBloqueoCtaIsmael.setEnabled(false);
            btnRegionCriticaIsmael.setEnabled(false);
            lblBloqueoCtaIsmael.setText("EN USO POR EL PROCESO:" + Integer.toString(mensaje.getK()));

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    lblBloqueoCtaIsmael.setEnabled(true);
                    btnRegionCriticaIsmael.setEnabled(true);
                    lblBloqueoCtaIsmael.setText("LIBERADO");
                    regionCriticaIsmaelEnUso = false;
                    saldoIsmael--;
                    lblSaldoIsmael.setText(String.format("Saldo: $%f", saldoIsmael));
                    try {
                        enviarRespuesta(mensaje);
                    } catch (IOException ex) {
                        Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    colaPeticionesRegionCriticaIsmael.remove(mensaje);
                    UtilsAlgoritmos.actualizaLista(listColaPeticionesIsmael, colaPeticionesRegionCriticaIsmael);
                    for (Mensaje m : colaPeticionesRegionCriticaIsmael) {
                        recibirMensajesRegionCriticaIsmael(m);
                    }

                }
            });

        }
    }

    public boolean enviarMensaje(int regionCritica) {
        boolean centinela = false;
        Mensaje mensaje = new Mensaje();
        mensaje.setK(this.proceso.numero);
        mensaje.setTk(regionCritica);
        mensaje.setDatos(String.format("proceso:%s desea retirar $1.0", Integer.toString(this.proceso.numero)));
        try {
            UDPClient client = new UDPClient(this.coordinador.direccion, this.coordinador.getPuerto());
            String respuesta = client.send(mensaje);
            if (respuesta != null) {
                if (respuesta.contains("OK")) {
                    if (regionCritica == 1) {
                        this.lblBloqueoCtaGinna.setText("OK");
                        this.btnRegionCriticaGinna.setEnabled(true);
                    }
                    if (regionCritica == 2) {
                        this.lblBloqueoCtaIsmael.setText("OK");
                        this.btnRegionCriticaIsmael.setEnabled(true);
                    }

                }
                if (respuesta.contains("WAIT")) {
                    if (regionCritica == 1) {
                        this.lblBloqueoCtaGinna.setText("EN ESPERA");
                        this.btnRegionCriticaGinna.setEnabled(false);
                    }
                    if (regionCritica == 2) {
                        this.lblBloqueoCtaIsmael.setText("EN ESPERA");
                        this.btnRegionCriticaIsmael.setEnabled(false);
                    }
                }
            }
            centinela = true;
        } catch (SocketException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        }

        return centinela;
    }

    public void enviarRespuesta(Mensaje mensaje) throws IOException {
        try {
            mensaje.setEnEspera(false);
            UDPClient client = new UDPClient(mensaje.getDireccion(), FrameCentralizado.puertoInicial + mensaje.getK());
            mensaje.setSaldoRegionCriticaGinna(saldoGinna);
            mensaje.setSalgoRegionCriticaIsmael(saldoIsmael);
            String respuesta = client.send(mensaje);

        } catch (SocketException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CentralizadoMutex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recepcionAlgoritmoAnillo(Mensaje mensaje) {
        System.out.println("anillo" + mensaje);
        if (mensaje.getDatos().contains("PARTICIPANTE")) {
           //coordinador.setIsCoordinador(false);
           //coordinador.setEstatus("DEAD"); 
            coordinador.setIsDead(true);
            CoordinadorAnillo.actualizaEstatus(coordinador);            
            if (mensaje.getK() < this.proceso.getNumero()) {
                CoordinadorAnillo.actualizaEstatus(mensaje.getK(), "NO PARTICIPANTE", false);
                this.proceso.setEstatus("PARTICIPANTE");      
                CoordinadorAnillo.actualizaEstatus(proceso);         
                mensaje.setK(this.proceso.getNumero());
                this.recorreAnillo(proceso,mensaje);
            
            } else {
                if (mensaje.getK() > this.proceso.getNumero()) {
                    this.proceso.setEstatus("NO PARTICIPANTE");
                    CoordinadorAnillo.actualizaEstatus(this.proceso);
                    CoordinadorAnillo.actualizaEstatus(this.coordinador.getNumero(), "DEAD", false);
                    CoordinadorAnillo.actualizaEstatus(mensaje.getK(), "PARTICIPANTE", true);
                    this.recorreAnillo(proceso, mensaje);
                } else {
                    if (mensaje.getK() == this.proceso.getNumero()) {
                        coordinador.setIsCoordinador(false);
                        coordinador.setEstatus("DEAD"); 
                        CoordinadorAnillo.actualizaEstatus(coordinador);
                        this.proceso.setEstatus("");
                        this.proceso.isCoordinador = true;
                        this.coordinador = proceso;
                        CoordinadorAnillo.actualizaEstatus(this.proceso);
                    }

                }

            }
            
        }else{
            
               if (mensaje.getDatos().contains("COORDINADOR")){
                   CoordinadorAnillo.actualizaEstatus(this.proceso.puerto,"NO PARTICIPANTE",true); 
                       CoordinadorAnillo.actualizaEstatus(mensaje.getK(),"",true); 
                        this.recorreAnillo(proceso,mensaje);
               }
        
    }
        UtilsAlgoritmos.actualizaListaProcesos(this.listProcesosActivos, CoordinadorAnillo.procesos);
    }

    public void recorreAnillo(Proceso proceso, Mensaje mensaje) {
        
        //proceso.setEstatus("PARTICIPANTE");
        
//        for (Proceso p : CoordinadorAnillo.procesos) {
//            if (proceso.getNumero() == p.getNumero()) {
//                p.setEstatus(proceso.getEstatus());
//            }
//            if (p.isCoordinador && isDead) {
//                p.setEstatus("DEAD");
//            }
//            if (p.getNumero() == proceso.getNumero() + 1) {
//                mandaMensajeEleccion(proceso, p);
//            }
//        }
        Proceso siguiente=CoordinadorAnillo.getSiguienteProceso(proceso);
        mandaMensajeEleccion(proceso, siguiente,  mensaje);
        //UtilsAlgoritmos.actualizaListaProcesos(this.listProcesosActivos, CoordinadorAnillo.procesos);
    }

    public void mandaMensajeEleccion(Proceso proceso, Proceso siguiente, Mensaje mensaje) {
        String response = "";       
        try {
            UDPClient ping = new UDPClient(siguiente.getDireccion(), siguiente.puerto, 5000);
            response = ping.send(mensaje);

        } catch (SocketException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        }      
        UtilsAlgoritmos.actualizaListaProcesos(this.listProcesosActivos, CoordinadorAnillo.procesos);
    }

    public void mandaMensajeCoordinador(Proceso proceso, Proceso siguiente) {
        Mensaje m = new Mensaje();
        m.setK(proceso.numero);
        m.TIPO_MENSAJE = 3;
        m.setDatos(proceso.getEstatus());
        m.setEnEspera(true);
        String response = "";
        boolean isAlive = false;
        try {
            UDPClient ping = new UDPClient(siguiente.getDireccion(), siguiente.puerto, 5000);
            response = ping.send(m);
        } catch (SocketException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameCentralizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilsAlgoritmos.actualizaListaProcesos(this.listProcesosActivos, CoordinadorAnillo.procesos);
    }

}
