package logica.buscaminas;

/**
 *
 * @author windows
 */
public class Casilla {
    private boolean tieneBomba;
    private int [] posicion = new int[2];
    private int cantidadBombasEnRadio;

    public boolean isTieneBomba() {
        return tieneBomba;
    }

    public void setTieneBomba(boolean tieneBomba) {
        this.tieneBomba = tieneBomba;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    public int getCantidadBombasEnRadio() {
        return cantidadBombasEnRadio;
    }

    public void setCantidadBombasEnRadio(int cantidadBombasEnRadio) {
        this.cantidadBombasEnRadio = cantidadBombasEnRadio;
    }
    
}
