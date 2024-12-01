package Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.vista;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Controlador implements ActionListener {

    ClienteDAO dao = new ClienteDAO();
    Cliente cliente = new Cliente();
    vista vista = new vista();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador (vista v){
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
    
    }

    public void nuevo() {
        vista.txtid.setText("");
        vista.txtNombres.setText("");
        vista.txtDireccion.setText("");
        vista.txtFecha.setText("");
        vista.txtValor.setText("");
        vista.txtNombres.requestFocus();
    }

    public void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
             i=i-1;
        }

    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecione una fila");
        } else {
            int idcliente = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
            dao.Delete(idcliente);
            //System.out.println("El resultado es " + idcliente );
            JOptionPane.showMessageDialog(vista, "Usuario Eliminado");
        }
        limpiarTabla();
    }

    public void add() {
        String nombrecliente = vista.txtNombres.getText();
        String direccion = vista.txtDireccion.getText();
        String fechaservicio = vista.txtFecha.getText();
        String valorservicio = vista.txtValor.getText();
        cliente.setNombrecliente(nombrecliente);
        cliente.setDireccion(direccion);
        cliente.setFechaservicio(fechaservicio);
        cliente.setValorservicio(valorservicio);
        int r = dao.agregar(cliente);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Cliente agregado con exitoo");
        } else {
            JOptionPane.showMessageDialog(vista, "Error, no fue posible agregar cliente");
        }
        limpiarTabla();
    }

    public void actualizar() {
        if (vista.txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro");
        } else {
            int id = Integer.parseInt(vista.txtid.getText());
            String Nombres = vista.txtNombres.getText();
            String Direccion = vista.txtDireccion.getText();
            String Fecha = vista.txtFecha.getText();
            String Valor = vista.txtValor.getText();
            cliente.setIdcliente(id);
            cliente.setNombrecliente(Nombres);
            cliente.setDireccion(Direccion);
            cliente.setFechaservicio(Fecha);
            cliente.setValorservicio(Valor);
            int r = dao.Actualizar(cliente);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizado con exito");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        limpiarTabla();
    }

    public void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Cliente> lista = dao.Listar();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdcliente();
            objeto[1] = lista.get(i).getNombrecliente();
            objeto[2] = lista.get(i).getDireccion();
            objeto[3] = lista.get(i).getFechaservicio();
            objeto[4] = lista.get(i).getValorservicio();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== vista.btnListar){
        limpiarTabla();
        listar(vista.tabla);
        nuevo();                
        }
        if (e.getSource()==vista.btnAgregar){
            if(vista.txtNombres.getText().equals("")||vista.txtDireccion.getText().equals("")||
                    vista.txtFecha.getText().equals("")||vista.txtValor.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"No se permite campos vacios");
            }
            else{
                add();
                listar(vista.tabla);
                nuevo();
            }
        
        }
        if (e.getSource()== vista.btnEditar){
            int fila = vista.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Seleccione una fila");
            }else{
                int id= Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                String nombres= (String)vista.tabla.getValueAt(fila, 1);
                String direccion= (String)vista.tabla.getValueAt(fila, 2);
                String fecha= (String)vista.tabla.getValueAt(fila, 3);
                String valor= (String)vista.tabla.getValueAt(fila, 4);
                vista.txtid.setText(""+id);
                vista.txtNombres.setText(nombres);
                vista.txtDireccion.setText(direccion);
                vista.txtFecha.setText(fecha);
                vista.txtValor.setText(valor);
            }
        
        }
        
        if(e.getSource()== vista.btnActualizar){
        actualizar();
        listar(vista.tabla);
        nuevo();
        }
        if(e.getSource()== vista.btnEliminar){
        delete();
        listar(vista.tabla);
        nuevo();
        
        }
    
    }

}
