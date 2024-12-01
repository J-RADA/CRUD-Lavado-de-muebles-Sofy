
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection conexion;
    Conexion conectar = new Conexion();
    Cliente p = new Cliente();
    
    public List Listar(){
        List<Cliente>datos=new ArrayList<>();
        try {
            conexion = conectar.getConnection();
            ps = conexion.prepareStatement("select * from cliente");
            rs= ps.executeQuery();
            while (rs.next()){
            Cliente p = new Cliente();
            p.setIdcliente(rs.getInt(1));
            p.setNombrecliente(rs.getString(2));
            p.setDireccion(rs.getString(3));
            p.setFechaservicio(rs.getString(4));
            p.setValorservicio(rs.getString(5));
            datos.add(p);
                       
            }
        }catch(Exception e){
            System.out.println("No fue posible obtener la lista");
            
        }
        return datos;
    
    }
    public int  agregar(Cliente per){
        int r =0;
        String sql= "insert into cliente(nombrecliente,direccion,fechaservicio,valorservicio)values(?,?,?,?)";
        try{
        conexion = conectar.getConnection();
        ps = conexion.prepareStatement(sql);
        ps.setString(1, per.getNombrecliente());
        ps.setString(2,per.getDireccion());
        ps.setString(3,per.getFechaservicio() );
        ps.setString(4, per.getValorservicio());
        r = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("No fue posible agrear el cliente " +e);
        }
        
        
        
        return r;
    }
    public int Actualizar(Cliente per){
    int r =0;
    String sql = "update cliente set nombrecliente=?, direccion=?,fechaservicio=?,valorservicio=? where idcliente=?";
    try{
        conexion= conectar.getConnection();
        ps = conexion.prepareStatement(sql);
        ps.setString(1,per.getNombrecliente());
        ps.setString(2, per.getDireccion());
        ps.setString(3, per.getFechaservicio());
        ps.setString(4, per.getValorservicio());
        ps.setInt(5, per.getIdcliente());
        r=ps.executeUpdate();
        if(r==1){
            return 1;            
        }
        else{
            return 0;
        }
        
    }catch(Exception e){
        System.out.println("No fue posible actualizar el registro"+ e);
    }
    return r;
    }
    
    public int Delete(int idcliente){
        int r=0;
        String sql="delete from  cliente where idcliente="+idcliente;
        try{
            conexion=conectar.getConnection();
            ps=conexion.prepareStatement(sql);
            r =ps.executeUpdate();
        }catch(Exception e){
            System.out.println("No fue posible borrar el registro"+e);
        }
        return r;
    }
    
}
