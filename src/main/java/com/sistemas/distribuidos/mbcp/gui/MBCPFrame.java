/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp.gui;

import com.sistemas.distribuidos.mbcp.UDPClient;
import com.sistemas.distribuidos.mbcp.UDPServer;
import com.sistemas.distribuidos.mbcp.implementacion.Mensaje;
import com.sistemas.distribuidos.mbcp.implementacion.MensajeListen;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author lomik
 */
public class MBCPFrame extends javax.swing.JFrame {

    /**
     * Creates new form MBCPFrame
     */
    public MBCPFrame() {
        this.listener = new MensajeRecibidoListener(listMensajesRecibidos);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnviarProceso1 = new javax.swing.JButton();
        txtMensaje = new javax.swing.JTextField();
        lblRespuesta = new javax.swing.JLabel();
        lblSeleccionarProceso = new javax.swing.JLabel();
        btnLanzar = new javax.swing.JButton();
        lblEstatus = new javax.swing.JLabel();
        comboProcesos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMensajesEnviados = new javax.swing.JList<>();
        btnEnviarProceso2 = new javax.swing.JButton();
        btnEnviarProceso3 = new javax.swing.JButton();
        btnEnviarProceso4 = new javax.swing.JButton();
        btnEnviarProceso5 = new javax.swing.JButton();
        btnEnviarProceso6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMensajesRecibidos = new javax.swing.JList<>();
        lblRespuesta1 = new javax.swing.JLabel();
        lblFifo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listFifo = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEnviarProceso1.setText("Enviar Proceso 1");
        btnEnviarProceso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso1ActionPerformed(evt);
            }
        });

        lblRespuesta.setText("MENSAJES ENVIADOS");

        lblSeleccionarProceso.setText("SELECCIONE UN PROCESO");

        btnLanzar.setText("INICIAR");
        btnLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarActionPerformed(evt);
            }
        });

        lblEstatus.setText("Estado");

        comboProcesos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PROCESO 1", "PROCESO 2", "PROCESO 3", "PROCESO 4", "PROCESO 5", "PROCESO 6" }));

        jScrollPane1.setViewportView(listMensajesEnviados);

        btnEnviarProceso2.setText("Enviar Proceso 2");
        btnEnviarProceso2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso2ActionPerformed(evt);
            }
        });

        btnEnviarProceso3.setText("Enviar Proceso 3");
        btnEnviarProceso3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso3ActionPerformed(evt);
            }
        });

        btnEnviarProceso4.setText("Enviar Proceso 4");
        btnEnviarProceso4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso4ActionPerformed(evt);
            }
        });

        btnEnviarProceso5.setText("Enviar Proceso 5");
        btnEnviarProceso5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso5ActionPerformed(evt);
            }
        });

        btnEnviarProceso6.setText("Enviar Proceso 6");
        btnEnviarProceso6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarProceso6ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listMensajesRecibidos);

        lblRespuesta1.setText("MENSAJES RECIBIDOS");

        lblFifo.setForeground(new java.awt.Color(255, 51, 51));
        lblFifo.setText("MENSAJES EN ESPERA");

        jScrollPane3.setViewportView(listFifo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMensaje)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEnviarProceso1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEnviarProceso2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEnviarProceso3))
                            .addComponent(lblRespuesta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRespuesta1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEnviarProceso4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviarProceso5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnviarProceso6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblSeleccionarProceso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboProcesos, javax.swing.GroupLayout.Alignment.LEADING, 0, 137, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLanzar)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(240, 240, 240))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFifo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane3)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSeleccionarProceso)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLanzar))
                .addGap(7, 7, 7)
                .addComponent(lblEstatus)
                .addGap(28, 28, 28)
                .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarProceso1)
                    .addComponent(btnEnviarProceso2)
                    .addComponent(btnEnviarProceso3)
                    .addComponent(btnEnviarProceso4)
                    .addComponent(btnEnviarProceso5)
                    .addComponent(btnEnviarProceso6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRespuesta)
                    .addComponent(lblRespuesta1)
                    .addComponent(lblFifo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnEnviarProceso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso1ActionPerformed
        this.enviarMensaje(1, procesoSeleccionado, puertoInicial + 1, this.txtMensaje.getText());
    }//GEN-LAST:event_btnEnviarProceso1ActionPerformed

    private void enviarMensaje(int procesoDestino, int procesoSeleccionado, int puertoServidor, String mensajeStr) {
        try {
            // TODO add your handling code here:
            numeroMensaje++;
            Mensaje mensaje = new Mensaje(numeroMensaje, procesoSeleccionado, numeroMensaje, mensajeStr);
            String direccion = this.udpServer.getDirecciones().get(procesoDestino);
            if (direccion.equals("")) {
                JOptionPane.showMessageDialog(null, "No se conoce la direeccion logica del proceso porque aun no se han recibido mensajes");
            } else {
                UDPClient client = new UDPClient(direccion, puertoServidor);
                String respuesta = client.send(mensaje);
                this.mensajesEnviados.add(mensaje);
                String[] enviadosStr = new String[mensajesEnviados.size()];
                int i = 0;
                for (Mensaje m : mensajesEnviados) {
                    enviadosStr[i] = m.toString();
                    i++;
                }
                this.listMensajesEnviados.setListData((String[]) enviadosStr);
                this.lblRespuesta.setText(respuesta);
            }
        } catch (SocketException ex) {
            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int procesoSeleccionado;
    private int puertoInicial = 6788;
    private MensajeRecibidoListener listener;
    private Thread threadReceive;
    private UDPServer udpServer;
    private int numeroMensaje;
    private ArrayList<Mensaje> mensajesEnviados = new ArrayList();
    private ArrayList<Mensaje> mensajesRecibidos = new ArrayList();

    private void btnLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarActionPerformed
        try {
            listener = new MensajeRecibidoListener(listMensajesRecibidos);
            procesoSeleccionado = (this.comboProcesos.getSelectedIndex() + 1);
            int puertoProceso = puertoInicial + (procesoSeleccionado);
            udpServer = new UDPServer(puertoProceso, this.mensajesRecibidos, this.listener);
            // TODO add your handling code here:
            threadReceive = new Thread(udpServer);
            threadReceive.start();
            this.lblSeleccionarProceso.setText(this.comboProcesos.getSelectedItem().toString() + " INICIADO EN PUERTO:" + puertoProceso);
            this.btnLanzar.setEnabled(false);

            switch (procesoSeleccionado) {
                case 1:
                    this.btnEnviarProceso1.setEnabled(false);
                    break;
                case 2:
                    this.btnEnviarProceso2.setEnabled(false);

                    break;

                case 3:
                    this.btnEnviarProceso3.setEnabled(false);
                    break;

                case 4:
                    this.btnEnviarProceso4.setEnabled(false);

                    break;

                case 5:
                    this.btnEnviarProceso5.setEnabled(false);

                    break;

                case 6:
                    this.btnEnviarProceso6.setEnabled(false);

                    break;

            }

        } catch (SocketException ex) {
            Logger.getLogger(MBCPFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLanzarActionPerformed

    private void btnEnviarProceso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso2ActionPerformed
        // TODO add your handling code here:
        this.enviarMensaje(2,procesoSeleccionado, puertoInicial + 2, this.txtMensaje.getText());

    }//GEN-LAST:event_btnEnviarProceso2ActionPerformed

    private void btnEnviarProceso3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso3ActionPerformed
        // TODO add your handling code here:
        this.enviarMensaje(3,procesoSeleccionado, puertoInicial + 3, this.txtMensaje.getText());
    }//GEN-LAST:event_btnEnviarProceso3ActionPerformed

    private void btnEnviarProceso4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso4ActionPerformed
        // TODO add your handling code here:
        this.enviarMensaje(4,procesoSeleccionado, puertoInicial + 4, this.txtMensaje.getText());
    }//GEN-LAST:event_btnEnviarProceso4ActionPerformed

    private void btnEnviarProceso5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso5ActionPerformed
        // TODO add your handling code here:
        this.enviarMensaje(5,procesoSeleccionado, puertoInicial + 5, this.txtMensaje.getText());
    }//GEN-LAST:event_btnEnviarProceso5ActionPerformed

    private void btnEnviarProceso6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarProceso6ActionPerformed
        // TODO add your handling code here:
        this.enviarMensaje(6,procesoSeleccionado, puertoInicial + 6, this.txtMensaje.getText());
    }//GEN-LAST:event_btnEnviarProceso6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MBCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MBCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MBCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MBCPFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MBCPFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarProceso1;
    private javax.swing.JButton btnEnviarProceso2;
    private javax.swing.JButton btnEnviarProceso3;
    private javax.swing.JButton btnEnviarProceso4;
    private javax.swing.JButton btnEnviarProceso5;
    private javax.swing.JButton btnEnviarProceso6;
    private javax.swing.JButton btnLanzar;
    private javax.swing.JComboBox<String> comboProcesos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEstatus;
    private javax.swing.JLabel lblFifo;
    private javax.swing.JLabel lblRespuesta;
    private javax.swing.JLabel lblRespuesta1;
    private javax.swing.JLabel lblSeleccionarProceso;
    private javax.swing.JList<String> listFifo;
    private javax.swing.JList<String> listMensajesEnviados;
    private javax.swing.JList<String> listMensajesRecibidos;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}

class MensajeRecibidoListener implements MensajeListen {

    ArrayList<Mensaje> recibidos;

    javax.swing.JList<String> listMensajesRecibidos;

    public MensajeRecibidoListener() {
        recibidos = new ArrayList();
    }

    @Override
    public void agregarMensaje(Mensaje mensaje) {
        recibidos.add(mensaje);

        String[] enviadosStr = new String[recibidos.size()];
        int i = 0;
        for (Mensaje m : recibidos) {
            enviadosStr[i] = m.toString();
            i++;
        }
        this.listMensajesRecibidos.setListData((String[]) enviadosStr);

    }

    public MensajeRecibidoListener(JList<String> listMensajesRecibidos) {
        this();
        this.listMensajesRecibidos = listMensajesRecibidos;
    }

}
