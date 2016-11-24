/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.InetAddress;
import java.net.ServerSocket;
import javafx.scene.input.KeyCode;
import javax.swing.JOptionPane;
import nondish.*;

/**
 *
 * @author Carlo
 */
public class TCSKMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerSocket socket = new ServerSocket(7777, 10, InetAddress.getLocalHost());
            NonDishList ndList = new NonDishList();
            ndList.loadNonDishToJTable();
            ndList.setCenterScreen();
            ndList.setVisible(true);
        }catch(java.net.BindException b){
            JOptionPane.showMessageDialog(null,"Application is already running...");
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
