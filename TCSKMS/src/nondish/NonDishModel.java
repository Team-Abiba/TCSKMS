/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nondish;
import java.sql.*;
import javax.swing.*;
import db.DBUtilities;

public class NonDishModel {
    private int nonDishID;
    private String date;
    private String nonDishName;
    private String brandName;
    private int qty;
    private float price;
    private String supplier;
    private String status;
    
    DBUtilities dbu;
    static Connection conn;
    
    
    public int getNonDishID(){
        return nonDishID;
    }
    public void setNonDishID(int id){
        nonDishID = id;
    }
    
    public String getDateEdited(){
        return date;
    }
    public void setDateEdited(String dateEditted){
        date = dateEditted;
    }
    
    public String getNonDishName(){
        return nonDishName;
    }
    public void setNonDishName(String ndName){
        nonDishName = ndName;
    }
    
    public String getBrandName(){
        return brandName;
    }
    public void setBrandName(String brand){
        brandName = brand;
    }
    
    public int getQty(){
        return qty;
    }
    public void setQty(int quantity){
        qty = quantity;
    }
    
    public float getPrice(){
        return price;
    }
    public void setPrice(float ndPrice){
        price = ndPrice;
    }
    
    public String getSupplier(){
        return supplier;
    }
    public void setSupplier(String supplierName){
        supplier = supplierName;
    }
    
    public String getStatus(){
        return status;
    }
    public void setStatus(String ndStatus){
        status = ndStatus;
    }
    
    //adds a new non dish item and its details  
    public int addNonDish(){
        int affectedRows = 0;
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL(); //DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected.");
            String query;
            query = "insert into nondish (nondish_name, brand_name,"
                    + " supplier, date_time_edited, qty, price, status) values(?,?,?,STR_TO_DATE(?,'%Y/%c/%e %T'),?,?,?)";
            PreparedStatement addPrep = conn.prepareStatement(query);
            //prep.setInt(1, Integer.parseInt(nonDishID));
            addPrep.setString(1, nonDishName);
            addPrep.setString(2, brandName);
            addPrep.setString(3, supplier);
            addPrep.setString(4,date);
            addPrep.setInt(5, qty);
            addPrep.setFloat(6, price);
            addPrep.setString(7, status);
            
            affectedRows = addPrep.executeUpdate();
            System.out.println("Saved Row: " +affectedRows);
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            
        }
        return affectedRows;
    }
    //sets the status of a non dish item from Ok to Deleted
    public int deleteNonDish(){
        int affectedRows = 0;
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected.");
            String query;
            query = "update nondish set status=? where nondish_id=?";
            PreparedStatement deletePrep = conn.prepareStatement(query);
            deletePrep.setString(1, status);
            deletePrep.setInt(2, nonDishID);
            affectedRows = deletePrep.executeUpdate();
            System.out.println("Updated Row: " +affectedRows);
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            
        }
        return affectedRows;
    }
    //loads details of one non dish
    public void loadOneNonDish(){
        ResultSet rs=null;
        Statement loadOneNonDish;
        String query;
        
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected");
            query = "SELECT * FROM nondish WHERE nondish_id ="+this.getNonDishID();
            loadOneNonDish = conn.createStatement();
            rs = loadOneNonDish.executeQuery(query);
            rs.next();
            this.setNonDishID(rs.getInt("nondish_id"));
            setDateEdited(rs.getString("date_time_edited"));
            setNonDishName(rs.getString("nondish_name"));
            setBrandName(rs.getString("brand_name"));
            setSupplier(rs.getString("supplier"));
            setQty(rs.getInt("qty"));
            setPrice(rs.getFloat("price"));
            conn.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    //loads all non dish to non dish list
    public ResultSet loadNonDish(){
        ResultSet rs = null;
        Statement loadAllNonDish;
        String query;
        
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected");
            query = "select nondish_id AS CodeNum, nondish_name AS Name, brand_name AS BrandName,price AS Price,qty AS Quantity,"
                    + "supplier AS Supplier,date_time_edited AS Date from nondish where status = 'OK' LIMIT 10 OFFSET 0";
            loadAllNonDish = conn.createStatement();
            rs= loadAllNonDish.executeQuery(query);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            
        }
        
    return rs;  
    }
    //updates a non dish's detail
    public int updateNonDish(){
        int affectedRows = 0;
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected.");
            String query;
            query = "update nondish set nondish_name=?,brand_name=?,supplier=?"
                    + ",date_time_edited=NOW(),qty=?,price=? where nondish_id=?";
            
            PreparedStatement updatePrep = conn.prepareStatement(query);
            updatePrep.setString(1, nonDishName);
            updatePrep.setString(2, brandName);
            updatePrep.setString(3, supplier);
            updatePrep.setInt(4, qty);
            updatePrep.setFloat(5, price);
            updatePrep.setInt(6, nonDishID);
            affectedRows = updatePrep.executeUpdate();
            System.out.println("Updated Row: " +affectedRows);
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            
        }
        return affectedRows;
    }  
    
    public int checkDuplicate(){
        int returnedRow = 0;
        boolean rsCheck;
        ResultSet rs;
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();
            System.out.println("DB Connected.");
            String query;
            query = "select * from nondish where nondish_name=? and supplier=? and status=?";
            
            PreparedStatement checkPrep = conn.prepareStatement(query);
            checkPrep.setString(1, nonDishName);
            checkPrep.setString(2, supplier);
            checkPrep.setString(3, "OK");
            
            rs = checkPrep.executeQuery();
            rsCheck = rs.next();
            
            if(rsCheck == true){
                returnedRow = 1;
            }else{
                returnedRow = 0;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        return returnedRow;
    }
    
    public ResultSet loadTenNonDish(int os){
        ResultSet rs = null;
        Statement loadAllNonDish;
        String query;
        
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected");
            query = "select nondish_id AS CodeNum, nondish_name AS Name, brand_name AS BrandName,price AS Price,qty AS Quantity,"
                    + "supplier AS Supplier,date_time_edited AS Date from nondish where status = 'OK' LIMIT 10 OFFSET "+os;
            loadAllNonDish = conn.createStatement();
            rs= loadAllNonDish.executeQuery(query);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            
        }
        
    return rs;  
    }
    
    public int checkHaveResult(int os){
        int returnedRow = 0;
        boolean rsCheck;
        ResultSet rs;
        Statement loadAllNonDish;
        String query;
        
        try{
            dbu = new DBUtilities();
            conn = dbu.connectToMySQL();//DriverManager.getConnection(url,"root","");
            System.out.println("DB Connected");
            query = "select * from nondish where status = 'OK' LIMIT 10 OFFSET "+os;
            loadAllNonDish = conn.createStatement();
            rs = loadAllNonDish.executeQuery(query);
            rsCheck = rs.next();
            
            if(rsCheck == true){
                returnedRow = 1;
            }else{
                returnedRow = 0;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());  
        }
        
    return returnedRow;  
    }
}
