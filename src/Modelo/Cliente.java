
package Modelo;

public class Cliente {
    private int idcliente;
    private String nombrecliente;
    private String direccion;
    private String fechaservicio;
    private String valorservicio;

    public Cliente() {
    }

    public Cliente(int idcliente, String nombrecliente, String direccion, String fechaservicio, String valorservicio) {
        this.idcliente = idcliente;
        this.nombrecliente = nombrecliente;
        this.direccion = direccion;
        this.fechaservicio = fechaservicio;
        this.valorservicio = valorservicio;
    }

    public String getValorservicio() {
        return valorservicio;
    }

    public void setValorservicio(String valorservicio) {
        this.valorservicio = valorservicio;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaservicio() {
        return fechaservicio;
    }

    public void setFechaservicio(String fechaservicio) {
        this.fechaservicio = fechaservicio;
    }

        
    
}
