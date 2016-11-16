/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zerimar
 */
public class DBUtilities extends Config{
    static Connection conn;
    
    //MySQL connection
    public Connection connectToMySQL(){
        conn = null;
        try{
           conn = DriverManager.getConnection(url+dbName,username,password);
           
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        return conn;
    }
}
