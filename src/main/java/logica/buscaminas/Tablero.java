package logica.buscaminas;

import java.util.Random;

/**
 *
 * @author windows
 */
public final class Tablero {
    public Casilla[][] arregloCasillas;
    private Dificultad dificultad;
    private int cantidadBombas;

    public Tablero(Dificultad dificultad) {
        this.arregloCasillas = new Casilla[8][8];
        this.dificultad = dificultad;
        cargarTablero();
    }
    
    
    /***
     * Carga el arreglo de casillas con objetos de tipo Casilla con sus atributos.
     * Utilizandolo al principio de cada partida
     */
    public void cargarTablero(){
        Random random = new Random();
        switch (dificultad) {
            case FACIL -> cantidadBombas = 8;
            case MEDIO -> cantidadBombas = 16;
            case DIFICIL -> cantidadBombas = 26;
        }
        int cantidadBombasRestantes = cantidadBombas;
        for (Casilla[] arregloCasilla : arregloCasillas) {
            for (int j = 0; j < arregloCasilla.length; j++) {
                Casilla casilla = new Casilla();
                arregloCasilla[j] = casilla;
            } 
        }
        
        while(cantidadBombasRestantes != 0){
            int fila = random.nextInt(0, 8), columna = random.nextInt(0, 8);
            // Evitar zona central (ej: radio de 1 casilla alrededor del centro)
            boolean esCentro = Math.abs(fila - arregloCasillas.length/2) <= 1 && Math.abs(columna - arregloCasillas.length/2) <= 1;
            if (esCentro) continue;
            if(!arregloCasillas[fila][columna].isTieneBomba()){
                arregloCasillas[fila][columna].setTieneBomba(true);
                cantidadBombasRestantes--;
            }
        }
    }
    
    /***
     * Verifica cuantas bombas tiene una casilla en un radio de 1 hacia todas
     * direcciones
     * @return int De la cantidad bombas
     */
    public int manejarCantidadBombas(){
        return 0;
    }

    
    //Getters & Setters

    public Casilla[][] getArregloCasillas() {
        return arregloCasillas;
    }

    public void setArregloCasillas(Casilla[][] arregloCasillas) {
        this.arregloCasillas = arregloCasillas;
    }

    public int getCantidadBombas() {
        return cantidadBombas;
    }

    public void setCantidadBombas(int cantidadBombas) {
        this.cantidadBombas = cantidadBombas;
    }
    
}
