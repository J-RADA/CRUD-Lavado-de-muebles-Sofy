
package Modelo;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion { 
    String url = "jdbc:mysql://localhost:3306/lavadomuebles_sofy";
    String usuario = "root";
    String contrasena = "admin";
    
    Connection conexion;
    
    public Connection getConnection(){
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion=(Connection)DriverManager.getConnection(url,usuario, contrasena);
        }catch(Exception e)
        {
        JOptionPane.showMessageDialog(null,"Conexion fallida"+e);
        }            
        return conexion;
    
    }
    
}
