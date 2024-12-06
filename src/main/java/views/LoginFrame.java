package views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import services.CrudApi;

public class LoginFrame extends JFrame {
    private CrudApi servicios;

    public LoginFrame() {
        // Configuración del JFrame
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar la ventana

        // Estilo de la fuente
        Font modernFont = new Font("SansSerif", Font.PLAIN, 16);

        // Panel principal con fondo azul tenue
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(173, 216, 230)); // Azul tenue
        mainPanel.setLayout(new GridBagLayout()); // Centrar componentes
        add(mainPanel);

        // Crear panel interno para los elementos
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(new Color(255, 255, 255, 200)); // Fondo blanco con transparencia
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interno

        // Campos y etiquetas
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(modernFont);
        JTextField userField = new JTextField();
        userField.setFont(modernFont);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(modernFont);
        JPasswordField passField = new JPasswordField();
        passField.setFont(modernFont);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(modernFont);
        loginButton.setBackground(new Color(30, 144, 255)); // Azul brillante
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Acción del botón "Iniciar Sesión"
        loginButton.addActionListener(e -> {
            try {
                // Obtener credenciales
                String username = userField.getText();
                String password = new String(passField.getPassword());

                // Validar campos
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Llamada al servicio de login
                String token = servicios.login(username, password); // Asumiendo que tu API acepta username y password
                Main ventana = Main.getInstancia();
                ventana.setVisible(true);
                ventana.setToken(token);

                // Aquí podrías redirigir al usuario a otra ventana o funcionalidad
                dispose(); // Cerrar la ventana de login
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al iniciar sesión, ¿Usuario o contraseña incorrectos? Asegúrese de tener conexión a Internet", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes al panel
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(new JLabel()); // Espaciador
        loginPanel.add(loginButton);

        // Agregar el panel de login al centro del panel principal
        mainPanel.add(loginPanel);
    }

    public static void main(String[] args) {
        // Crear y mostrar el frame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
