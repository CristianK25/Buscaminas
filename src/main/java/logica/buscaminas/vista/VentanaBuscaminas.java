package logica.buscaminas.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logica.buscaminas.Dificultad;
import logica.buscaminas.Tablero;

/**
 *
 * @author windows
 */
public class VentanaBuscaminas extends javax.swing.JFrame {
    private Tablero tablero;
    private Dificultad dificultad;
    /**
     * Creates new form VentanaBuscaminas
     */
    public VentanaBuscaminas() {
        eleccionDificultad();
        initComponents();
        // En tu método initComponents() o constructor:
         panelCasillas.setLayout(new GridLayout(8, 8));
    
        // Aumenta el tamaño del panel para que quepan 8x8 botones de 28x28 px
         panelCasillas.setPreferredSize(new Dimension(28 * 8, 28 * 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(28, 28));
                final int fila = i, columna = j;
                boton.addActionListener(e -> manejarCasillas(new int[]{fila, columna}, boton));
                panelCasillas.add(boton);
            }
        }
        this.pack(); // Ajusta el JFrame al tamaño preferido de sus componentes
        this.setLocationRelativeTo(null); // Centra la ventana
    }

    private void eleccionDificultad(){
        dificultad = null;
        while (dificultad == null){
            dificultad = (Dificultad)JOptionPane.showInputDialog
            (null,
            "Selecciona la Dificultad",
            "Dificultad",
            JOptionPane.QUESTION_MESSAGE,
            null,
            Dificultad.values(),
            Dificultad.FACIL);
        
        // Si cierra el diálogo, muestra mensaje y repite
            if (dificultad == null) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Debes seleccionar una dificultad para continuar.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }else tablero = new Tablero(dificultad);
        }
    }
    
    private void manejarCasillas(int[] pos, JButton boton){
        int i = pos[0], j = pos[1];
        if (tablero.getArregloCasillas()[i][j].isTieneBomba()){
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        tituloPrincipal = new javax.swing.JLabel();
        panelCasillas = new javax.swing.JPanel();
        textoCantidadBombas = new javax.swing.JLabel();
        cantidadDeBombas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tituloPrincipal.setFont(new java.awt.Font("Segoe UI Black", 2, 36)); // NOI18N
        tituloPrincipal.setForeground(new java.awt.Color(0, 102, 102));
        tituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPrincipal.setText("Buscaminas");

        panelCasillas.setMinimumSize(new java.awt.Dimension(303, 256));

        javax.swing.GroupLayout panelCasillasLayout = new javax.swing.GroupLayout(panelCasillas);
        panelCasillas.setLayout(panelCasillasLayout);
        panelCasillasLayout.setHorizontalGroup(
            panelCasillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        panelCasillasLayout.setVerticalGroup(
            panelCasillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCasillas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(tituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(tituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCasillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        textoCantidadBombas.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        textoCantidadBombas.setForeground(new java.awt.Color(0, 102, 102));
        textoCantidadBombas.setText("Bombas:");

        cantidadDeBombas.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoCantidadBombas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidadDeBombas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCantidadBombas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadDeBombas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cantidadDeBombas;
    private javax.swing.JPanel panelCasillas;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel textoCantidadBombas;
    private javax.swing.JLabel tituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
