/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.distribuidos.mbcp;

import com.sistemas.distribuidos.mbcp.gui.MBCPFrame;
import java.net.SocketException;

/**
 *
 * @author Ginna Monserrat
 */
public class Main {
    
    public static void main(String[] args) throws SocketException{
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MBCPFrame().setVisible(true);
            }
        });
               
    }
}
