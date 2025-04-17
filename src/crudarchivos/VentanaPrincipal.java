package crudarchivos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    private final ClienteServicios servicio;
    private final JTable tablaClientes;
    private final DefaultTableModel modeloTabla;
    private final JButton btnAgregar;
    private final JButton btnEditar;
    private final JButton btnEliminar;

    public VentanaPrincipal() {
        servicio = new ClienteServicios();
        
        // Configuración de la ventana
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        
        // Configuración de la tabla
        String[] columnas = {"Cédula", "Nombre", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar Cliente");
        btnEditar = new JButton("Editar Cliente");
        btnEliminar = new JButton("Eliminar Cliente");
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar panel a la ventana
        add(panel);
        
        // Configurar eventos de botones
        configurarEventos();
        
        // Cargar datos iniciales
        actualizarTabla();
    }
    
    private void configurarEventos() {
        btnAgregar.addActionListener((ActionEvent e) -> {
            FormularioCliente formulario = new FormularioCliente(this, true);
            formulario.setVisible(true);
            if (formulario.getCliente() != null) {
                servicio.crearCliente(formulario.getCliente());
                actualizarTabla();
            }
        });
        
        btnEditar.addActionListener((ActionEvent e) -> {
            String cedulaStr = JOptionPane.showInputDialog(this, "Ingrese la cédula del cliente a editar:");
            if (cedulaStr != null && !cedulaStr.trim().isEmpty()) {
                try {
                    int cedula = Integer.parseInt(cedulaStr);
                    ArrayList<Cliente> clientes = servicio.leerClientes();
                    Cliente clienteEncontrado = null;
                    
                    for (Cliente c : clientes) {
                        if (c.getCedula() == cedula) {
                            clienteEncontrado = c;
                            break;
                        }
                    }
                    
                    if (clienteEncontrado != null) {
                        FormularioCliente formulario = new FormularioCliente(this, true, clienteEncontrado);
                        formulario.setVisible(true);
                        
                        if (formulario.getCliente() != null) {
                            servicio.actualizarCliente(formulario.getCliente());
                            actualizarTabla();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró un cliente con la cédula ingresada");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de cédula válido");
                }
            }
        });
        
        btnEliminar.addActionListener((ActionEvent e) -> {
            String cedulaStr = JOptionPane.showInputDialog(this, "Ingrese la cédula del cliente a eliminar:");
            if (cedulaStr != null && !cedulaStr.trim().isEmpty()) {
                try {
                    int cedula = Integer.parseInt(cedulaStr);
                    ArrayList<Cliente> clientes = servicio.leerClientes();
                    boolean clienteExiste = false;
                    
                    for (Cliente c : clientes) {
                        if (c.getCedula() == cedula) {
                            clienteExiste = true;
                            break;
                        }
                    }
                    
                    if (clienteExiste) {
                        int confirmacion = JOptionPane.showConfirmDialog(this,
                                "¿Está seguro de eliminar este cliente?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION);
                        
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            if (servicio.eliminarCliente(cedula)) {
                                actualizarTabla();
                                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(this, "No se pudo eliminar el cliente");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró un cliente con la cédula ingresada");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de cédula válido");
                }
            }
        });
    }
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<Cliente> clientes = servicio.leerClientes();
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getTelefono()
            };
            modeloTabla.addRow(fila);
        }
    }
}