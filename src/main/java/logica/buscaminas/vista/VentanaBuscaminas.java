package logica.buscaminas.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import logica.buscaminas.Casilla;
import logica.buscaminas.Dificultad;
import logica.buscaminas.Tablero;
/**
 *
 * @author windows
 */
public class VentanaBuscaminas extends javax.swing.JFrame {
    private Tablero tablero;
    private Dificultad dificultad;
    private JButton[][] botonesTablero;
    
    /**
     * Creates new form VentanaBuscaminas
     */
    public VentanaBuscaminas() {
        eleccionDificultad();
        initComponents();
        cargarBotones();
        this.setLocationRelativeTo(null); // Centra la ventana
    }

    /**
     * Reinicia la eleccion de dificultad, la carga de botones y del tablero en 
     * caso de que el jugador pierda
     */
    private void reiniciarJuego(){
        eleccionDificultad();
        panelCasillas.removeAll();
        cargarBotones();
    }
    
    /**
     * Carga los botones en el panel destinado a las casillas
     */
    private void cargarBotones(){
        botonesTablero = new JButton[8][8];
        // En tu método initComponents() o constructor:
         panelCasillas.setLayout(new GridLayout(8, 8));
    
        // Aumenta el tamaño del panel para que quepan 8x8 botones de 28x28 px
         panelCasillas.setPreferredSize(new Dimension(28 * 8, 28 * 8));
         
        //Inicializo los botones en el panel 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(28, 28));
                final int fila = i, columna = j;
                //Accion del boton al pulsarlo dependiendo si esta activado el modo bandera
                boton.addActionListener(e -> 
                        manejarCasillas(new int[]{fila, columna}, boton));
                botonesTablero[fila][columna] = boton;
                panelCasillas.add(boton);
            }
        }
        this.pack(); // Ajusta el JFrame al tamaño preferido de sus componentes
        cantidadDeBombas.setText(String.valueOf(tablero.getCantidadBombas()));
    }
    
    /***
     * JOptionPane con una lista para presentarle las dificultades 
     * disponibles al jugador
     */
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
    
    /**
     * Verifica en que modo esta la casilla y si tiene bandera o no. En caso de estar
     * en modo descubrir, llama al metodo manejar la cantidad de bombas para saber si 
     * la casilla tiene bombas, y en caso de no tener cuantas tiene en su radio
     * @param pos
     * @param boton 
     */
    private void manejarCasillas(int[] pos, JButton boton){
        int i = pos[0], j = pos[1];
        if (botonCambioModo.isSelected()){
            if("".equals(boton.getText())) {
                boton.setForeground(Color.red);
                boton.setText("🚩");
                cantidadDeBombas.setText(String.valueOf(Integer.parseInt(cantidadDeBombas.getText())-1));
            } else {
                boton.setText("");
                cantidadDeBombas.setText(String.valueOf(Integer.parseInt(cantidadDeBombas.getText())+1));
            }
        }else if("".equals(boton.getText())) {
            if (tablero.arregloCasillas[i][j].isTieneBomba()){
                mostrarBombas();
                JOptionPane.showMessageDialog(null,"¡¡PERDISTE!!","",JOptionPane.ERROR_MESSAGE,null);
                reiniciarJuego();
            }else
            efectoCascada(i,j);
            verificarVictoria();
        }       
    }
    
    private void verificarVictoria(){
        for(int i=0;i<botonesTablero.length;i++){
            for(int j=0;j<botonesTablero[i].length;j++){
                if(botonesTablero[i][j].isEnabled()) return;
                else if (banderasBienColocadas()){
                    mostrarBombas();
                    JOptionPane.showMessageDialog(null,"Has ganado");
                    reiniciarJuego();
                }
            }
        }
    }
    
    /**
     * Verifica si las banderas estan en lugares en donde hay bombas
     */
    private boolean banderasBienColocadas(){
        int cantidadBanderas = tablero.getCantidadBombas();
        for(int i=0;i<botonesTablero.length;i++){
            for(int j=0;j<botonesTablero[i].length;j++){
               if("🚩".equals(botonesTablero[i][j].getText()) && tablero.arregloCasillas[i][j].isTieneBomba())
                   cantidadBanderas--;
            }
        }
        return cantidadBanderas == 0;
    }
    
    /**
     * Muestra la posicion de todas las bombas del tablero
     */
    private void mostrarBombas(){
        for (int i=0; i<botonesTablero.length;i++){
            for (int j=0;j<botonesTablero[i].length;j++){
                if (tablero.arregloCasillas[i][j].isTieneBomba()){
                    botonesTablero[i][j].setForeground(Color.BLACK);
                    botonesTablero[i][j].setText("💣");
                    botonesTablero[i][j].setEnabled(false);
                }
            }
        }
    }
    
    /**
     * Verifica si el boton pulsado tiene bombas en un radio de 1 casilla. 
     * Si una casilla de 1 radio no tiene bombas, se verificara tambien si tiene 
     * bombas en un radio. y asi hasta que por lo menos tengan 1 bomba en ese radio.
     */
    private void efectoCascada(int filaInicial,int columnaInicial){
        Queue<int[]> cola = new LinkedList<>();
        cola.add(new int[]{filaInicial,columnaInicial});
        
        while (!cola.isEmpty()){
            int[] pos = cola.poll();
            int fila = pos[0], columna = pos[1];
            
            // Si la casilla ya fue revelada, la ignoramos
            if (tablero.arregloCasillas[fila][columna].isRevelada()) {
                continue;
            }
            
            // Marcar como revelada en el modelo
            tablero.arregloCasillas[fila][columna].setRevelada(true);
            
             // Contar bombas adyacentes
            int bombasAdyacentes = contarBombasAdyacentes(fila, columna);
            // Actualizar el botón en la UI
            if (bombasAdyacentes > 0) {
                Color color;
                switch (bombasAdyacentes) {
                    case 1: color = new Color(46, 125, 50);  // Verde oscuro
                            break;
                    case 2: color = new Color(21, 101, 192); // Azul oscuro
                            break;
                    case 3: color = new Color(211, 47, 47);  // Rojo oscuro
                            break;
                    case 4: color = new Color(106, 27, 154); // Púrpura oscuro
                            break;
                    default: color = Color.BLACK;
                }
                botonesTablero[fila][columna].setForeground(color);
                botonesTablero[fila][columna].setText(String.valueOf(bombasAdyacentes));
                botonesTablero[fila][columna].setOpaque(true);
                botonesTablero[fila][columna].setContentAreaFilled(false); // Fondo transparente
                botonesTablero[fila][columna].setBorderPainted(false); // Sin borde
            } else {
                botonesTablero[fila][columna].setText("");
            }
            botonesTablero[fila][columna].setEnabled(false);
            
            // Si no hay bombas alrededor, agregar vecinos a la cola
            if (bombasAdyacentes == 0) {
                for (int i = Math.max(0, fila - 1); i <= Math.min(7, fila + 1); i++) {
                    for (int j = Math.max(0, columna - 1); j <= Math.min(7, columna + 1); j++) {
                        // No agregar la misma casilla ni casillas con bandera
                        if ((i != fila || j != columna) && !"🚩".equals(botonesTablero[i][j].getText())) {
                            cola.add(new int[]{i, j});
                        }
                    }
                }
            } 
        }       
    }
    
    private int contarBombasAdyacentes(int fila,int columna){
        int bombas = 0;
        for (int i = Math.max(0, fila - 1); i <= Math.min(7, fila + 1); i++) {
            for (int j = Math.max(0, columna - 1); j <= Math.min(7, columna + 1); j++) {
                if (tablero.arregloCasillas[i][j].isTieneBomba()) {
                    bombas++;
                }
            }
        }
        return bombas;
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
        botonCambioModo = new javax.swing.JCheckBox();

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
        cantidadDeBombas.setBackground(new java.awt.Color(255, 255, 255));
        cantidadDeBombas.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        cantidadDeBombas.setForeground(new java.awt.Color(0, 102, 102));
        cantidadDeBombas.setCaretColor(new java.awt.Color(0, 102, 102));
        cantidadDeBombas.setEnabled(false);

        botonCambioModo.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        botonCambioModo.setForeground(new java.awt.Color(0, 102, 102));
        botonCambioModo.setText("Colocar Bandera");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCambioModo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCantidadBombas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadDeBombas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCambioModo))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox botonCambioModo;
    private javax.swing.JTextField cantidadDeBombas;
    private javax.swing.JPanel panelCasillas;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel textoCantidadBombas;
    private javax.swing.JLabel tituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
