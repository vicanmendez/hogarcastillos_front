/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

/**
 *
 * @author vicmn
 */
public class Main extends javax.swing.JFrame {
    public static Main instancia = null;
    private String token;
    /**
     * Creates new form Main
     */
    public Main() {
        
        initComponents();
        this.setBounds(0, 0, 1024, 760);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hogar Blanca Rubio de Rubio");

        jPanel1.setBackground(new java.awt.Color(230, 230, 242));
        jPanel1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jPanel1AncestorResized(evt);
            }
        });
        jPanel1.setLayout(new java.awt.BorderLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Gestión de Recibos de Pago");
        titulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        titulo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(titulo, java.awt.BorderLayout.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\vicmn\\OneDrive\\Documentos\\NetBeansProjects\\APIClient\\src\\main\\java\\resources\\menu.png")); // NOI18N
        jPanel1.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jButton1.setText("Residentes");
        panelMenu.add(jButton1);

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jButton2.setText("Socios");
        panelMenu.add(jButton2);

        jButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jButton3.setText("Recibos");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        panelMenu.add(jButton3);

        jPanel1.add(panelMenu, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1AncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jPanel1AncestorResized
        // TODO add your handling code here:
        jPanel1.setBounds((int) this.getAlignmentX(), (int) this.getAlignmentY(), this.getWidth(), this.getHeight());
    }//GEN-LAST:event_jPanel1AncestorResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    public static Main getInstancia() {
        if (instancia == null) { 
            instancia = new Main();
            return instancia;
        } else {
            return instancia;
        }
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}

