package logica.buscaminas;

/**
 *
 * @author windows
 */
public class Casilla {
    private boolean tieneBomba;
    private boolean casillaRevelada = false;
    private int [] posicion = new int[2];
    private int cantidadBombasEnRadio;

    public boolean isTieneBomba() {
        return tieneBomba;
    }

    public void setTieneBomba(boolean tieneBomba) {
        this.tieneBomba = tieneBomba;
    }

    public void setCasillaRevelada(boolean casillaRevelada) {
        this.casillaRevelada = casillaRevelada;
    }

    public boolean isCasillaRevelada() {
        return casillaRevelada;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public int getCantidadBombasEnRadio() {
        return cantidadBombasEnRadio;
    }

    public void setCantidadBombasEnRadio(int cantidadBombasEnRadio) {
        this.cantidadBombasEnRadio = cantidadBombasEnRadio;
    }
    
}
