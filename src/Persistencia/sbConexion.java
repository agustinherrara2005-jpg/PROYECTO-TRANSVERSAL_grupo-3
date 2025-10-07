/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sbConexion{
    private final String url;
    private final String usuario;
    private final String password;
    
    private static Connection conexion = null;  // lo que importa  
    
    public sbConexion(String url, String usr, String pass){     
        this.url= url;  
        usuario=usr;
        password=pass;
    }
    
    public Connection buscarConexion(){
        if (conexion==null) {  // si es la primera vez
            try {
                //cargamos las clases de mariadb que implementan JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");  // 1
                conexion = DriverManager.getConnection(url, usuario, password);  // 2
            } catch (SQLException | ClassNotFoundException ex) {  // si me olvide de importar la libreria // error al cargar los drivers
                System.out.println("No se puede conectar o no se puede cargar el driver. Error: "+ ex.getMessage());
            }
        }
        return conexion; // retorna la conexion establecida
    }

    public static Connection getConexion() {
        return conexion;
    } 
}