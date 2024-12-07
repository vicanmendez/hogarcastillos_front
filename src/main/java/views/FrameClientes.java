package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CrudApi;

public class FrameClientes extends JFrame {
    private final String token;
    private CrudApi servicios = new CrudApi();

    public FrameClientes(String token) {
        this.token = token;
        servicios.setToken(token);
        setTitle("Gestión de Socios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: Tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para alta de clientes
        JPanel panelAlta = crearPanelAlta();
        tabbedPane.addTab("Alta", panelAlta);

        // Panel para modificación de clientes
        JPanel panelModificacion = crearPanelModificacion();
        tabbedPane.addTab("Modificación", panelModificacion);

        // Panel para consulta de clientes
        JPanel panelConsulta = crearPanelConsulta();
        tabbedPane.addTab("Consulta", panelConsulta);

 

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelAlta() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        JTextField txtNombre = new JTextField();
        JTextField txtCI = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtDireccion = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Cédula:"));
        panel.add(txtCI);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                servicios.createClient(txtNombre.getText(), txtCI.getText(), txtTelefono.getText(),
                        txtEmail.getText(), txtDireccion.getText());
                JOptionPane.showMessageDialog(this, "Cliente creado con éxito.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear el cliente: " + ex.getMessage());
            }
        });

        panel.add(btnGuardar);
        return panel;
    }

    private JPanel crearPanelModificacion() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtCI = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtDireccion = new JTextField();

        panel.add(new JLabel("ID del Cliente:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Cédula:"));
        panel.add(txtCI);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> {
            try {
                int clientId = Integer.parseInt(txtId.getText());
                servicios.updateClient(clientId, txtNombre.getText(), txtCI.getText(),
                        txtTelefono.getText(), txtEmail.getText(), txtDireccion.getText());
                JOptionPane.showMessageDialog(this, "Cliente modificado con éxito.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar el cliente: " + ex.getMessage());
            }
        });

        panel.add(btnModificar);
        return panel;
    }

    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new BorderLayout());
        // Crear tabla
        String[] columnNames = {"ID", "Nombre", "Cédula", "Teléfono", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true); // Para que la tabla sea scrollable
        JScrollPane scrollPane = new JScrollPane(table);

        
        JButton btnConsultar = new JButton("Consultar Todos");

       btnConsultar.addActionListener(e -> {
            try {
                String response = servicios.listClients();
                JSONArray jsonArray = new JSONArray(response);

                // Limpiar datos anteriores
                model.setRowCount(0);

                // Procesar JSON y añadir a la tabla
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Object[] row = {
                            jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getString("ci"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("email"),
                    };
                    model.addRow(row);
                }

                JOptionPane.showMessageDialog(this, "Clientes consultados exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar clientes: " + ex.getMessage());
            }
        });

        panel.add(new JScrollPane(scrollPane), BorderLayout.CENTER);
        panel.add(btnConsultar, BorderLayout.SOUTH);
        return panel;
    }

   


}
