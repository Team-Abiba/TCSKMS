/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.InetAddress;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import login.Login;

/**
 *
 * @author zerimar
 */
public class TCSKMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try{
            ServerSocket socket = new ServerSocket(9999, 10, InetAddress.getLocalHost());
            Login l = new Login();
            l.setCenterScreen();
            l.setVisible(true);
        }catch(java.net.BindException b){
            JOptionPane.showMessageDialog(null,"Application is already running...");
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
