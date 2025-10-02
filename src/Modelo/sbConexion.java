/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;

/**
 *
 * @author kamil
 */
public class sbConexion {
    public static void main(String[] args)
    {
        try {
            Class.forName("com.ibm.db2.jcc DB2Driver");
            Connection con = DriverManager.getConnection("jdbc:db2 sample");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FROM employee");
            
            while(rs.next())
                System.out.println("Nombre:" + rs.getString(2)+" Nivel: " + rs.getInt(9));
            
            rs.close();
            stmt.close();
            con.close();
        }
        
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        catch(SQLException ex){
            ex.printStackTrace();}
        }
    }
