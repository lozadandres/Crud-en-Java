package crudarchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FormularioCliente extends JDialog {
    private final JTextField txtCedula;
    private final JTextField txtNombre;
    private final JTextField txtTelefono;
    private Cliente cliente;
    
    public FormularioCliente(Frame parent, boolean modal) {
        this(parent, modal, null);
    }
    
    public FormularioCliente(Frame parent, boolean modal, Cliente clienteExistente) {
        super(parent, modal);
        setTitle(clienteExistente == null ? "Agregar Cliente" : "Editar Cliente");
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);
        
        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);
        
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        
        add(panel);
        
        if (clienteExistente != null) {
            txtCedula.setText(String.valueOf(clienteExistente.getCedula()));
            txtCedula.setEditable(false);
            txtNombre.setText(clienteExistente.getNombre());
            txtTelefono.setText(String.valueOf(clienteExistente.getTelefono()));
        }
        
        btnGuardar.addActionListener((ActionEvent e) -> {
            if (validarCampos()) {
                cliente = new Cliente();
                cliente.setCedula(Integer.parseInt(txtCedula.getText()));
                cliente.setNombre(txtNombre.getText());
                cliente.setTelefono(Integer.parseInt(txtTelefono.getText()));
                dispose();
            }
        });
        
        btnCancelar.addActionListener((ActionEvent e) -> {
            cliente = null;
            dispose();
        });
    }
    
    private boolean validarCampos() {
        if (txtCedula.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() || 
            txtTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        
        try {
            Integer.parseInt(txtCedula.getText());
            Integer.parseInt(txtTelefono.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula y el teléfono deben ser números");
            return false;
        }
        
        return true;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
}