/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author lomik
 */
public class MBCPAlgoritmo implements MensajeListen {

    private int[] VTp;
    private int numeroProceso;
    private CopyOnWriteArrayList<Hm> CI;
    private int numeroMensaje;
    private ArrayList<Mensaje> mensajesConstruidos;

    public ArrayList<Mensaje> mensajesRecibidos;
    javax.swing.JList<String> listMensajesRecibidos;
    private CopyOnWriteArrayList<Mensaje> mensajesEnEspera;
    javax.swing.JList<String> listmensajesEnEspera;
    JLabel lblMbcp;

    public MBCPAlgoritmo(int numeroProceso, JList<String> listMensajesRecibidos, JList<String> listMensajesEspera, JLabel lblMbcp) {
        this.VTp = new int[6];
        this.numeroProceso = numeroProceso;
        this.CI = new CopyOnWriteArrayList();
        this.numeroMensaje = 0;
        this.mensajesConstruidos = new ArrayList();
        this.mensajesRecibidos = new ArrayList();
        this.listMensajesRecibidos = listMensajesRecibidos;
        this.lblMbcp = lblMbcp;
        this.mensajesEnEspera = new CopyOnWriteArrayList();
        this.listmensajesEnEspera = listMensajesEspera;

    }

    /**
     * Algortiritmo difusion
     *
     * @param mensajeStr
     * @return
     */
    public Mensaje construirMensaje(String mensajeStr) {

        this.numeroMensaje++;
        Mensaje mensaje = new Mensaje(numeroMensaje, this.numeroProceso, numeroMensaje, mensajeStr, this.CI);
        this.VTp[numeroProceso - 1]++;
        this.CI.clear();
        this.mensajesConstruidos.add(mensaje);
        return mensaje;

    }

    public ArrayList<Mensaje> getMensajesConstruidos() {
        return mensajesConstruidos;
    }

    public ArrayList<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void limpiar() {
        for (int i = 0; i < VTp.length; i++) {
            VTp[i] = 0;
        }
    }

    public String[] getListConstruidos() {
        String[] enviadosStr = new String[this.mensajesConstruidos.size()];
        int i = 0;
        for (Mensaje m : this.mensajesConstruidos) {
            enviadosStr[i] = m.toString();
            i++;
        }
        return enviadosStr;
    }

    public String[] getListRecibidos() {
        String[] enviadosStr = new String[this.mensajesConstruidos.size()];
        int i = 0;
        for (Mensaje m : this.mensajesConstruidos) {
            enviadosStr[i] = m.toString();
            i++;
        }
        return enviadosStr;
    }

    @Override
    public String toString() {
        String centinela = String.format("VT(%d)={%s}\nCI={%s}", this.numeroProceso, Arrays.toString(this.VTp), Hm.toStringHm(CI));
        return centinela;
    }

//    
//     private void enviarMensaje(int procesoDestino, int procesoSeleccionado, int puertoServidor, String mensajeStr) {
//        try {
//            // TODO add your handling code here:
//            ++;
//            Mensaje mensaje 
//            String direccion = this.udpServer.getDirecciones().get(procesoDestino);
//            if (direccion.equals("")) {
//                JOptionPane.showMessageDialog(null, "No se conoce la direeccion logica del proceso porque aun no se han recibido mensajes");
//            } else {
//                UDPClient client = new UDPClient(direccion, puertoServidor);
//                String respuesta = client.send(mensaje);
//                this.mensajesEnviados.add(mensaje);
//                String[] enviadosStr = new String[mensajesEnviados.size()];
//                int i = 0;
//                for (Mensaje m : mensajesEnviados) {
//                    enviadosStr[i] = m.toString();
//                    i++;
//                }
//                this.listMensajesEnviados.setListData((String[]) enviadosStr);
//                this.lblRespuesta.setText(respuesta);
//            }
//        } catch (SocketException ex) {
//            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    //   }
    @Override
    public boolean agregarMensaje(Mensaje mensaje) {
        boolean centinela = false;

        if (mensaje != null) {

            if (mensaje.getTk() == this.VTp[mensaje.getK() - 1] + 1
                    && entregaHm(mensaje)) {
                this.mensajesRecibidos.add(mensaje);
                this.VTp[mensaje.getK() - 1]++;
                //1er poda
                for (Hm ci : this.CI) {
                    if (ci.getL() == mensaje.getK()) {
                        this.CI.remove(ci);
                    }

                }

                this.CI.add(new Hm(mensaje.getK(), mensaje.getTk()));
                //2nd poda
                for (Hm ci : this.CI) {
                    for (Hm hm : mensaje.getHmi()) {
                        if (ci.getL() == hm.getL() && ci.getTl() == hm.getTl()) {
                            this.CI.remove(ci);
                        }
                    }
                }
                centinela = true;
                for (Mensaje m : this.mensajesEnEspera) {
                    if (m.getK() == mensaje.getK() && m.getTk() == mensaje.getTk()) {
                        this.mensajesEnEspera.remove(m);
                    }else
                        agregarMensaje(m);
                }

            } else {
                if(this.getIndexEspera(mensaje)==-1)
                     this.mensajesEnEspera.add(mensaje);
            }

//            this.mensajesRecibidos.add(mensaje);
            String[] enviadosStr = new String[this.mensajesRecibidos.size()];
            int i = 0;
            for (Mensaje m : mensajesRecibidos) {
                enviadosStr[i] = m.toString();
                i++;
            }
            this.listMensajesRecibidos.setListData((String[]) enviadosStr);

            String[] esperaStr = new String[this.mensajesEnEspera.size()];
            i = 0;
            for (Mensaje m : mensajesEnEspera) {
                esperaStr[i] = m.toString();
                i++;
            }

            this.listmensajesEnEspera.setListData((String[]) esperaStr);
            this.lblMbcp.setText(this.toString());
        }

        return centinela;
    }

    public boolean entregaHm(Mensaje mensaje) {
        boolean centinela = true;

        if (!mensaje.getHmi().isEmpty()) {
            for (Hm hm : mensaje.getHmi()) {
                if (hm.getTl() > this.VTp[hm.getL() - 1]) {
                    centinela = false;
                    break;
                }
            }
        }
        return centinela;
    }

    @Override
    public void agregaEnEspera() {
        //for (Mensaje m : this.mensajesEnEspera) {
          //  agregarMensaje(m);
       // }
    }
    
    
    public int getIndexEspera(Mensaje mensaje){
        int centinela=-1;
        int index=0;
         for (Mensaje m : this.mensajesEnEspera) {
                    if (m.getK() == mensaje.getK() && m.getTk() == mensaje.getTk()) {
centinela=index;
                    }
                    index++;
                }
         return centinela;

    }
}
