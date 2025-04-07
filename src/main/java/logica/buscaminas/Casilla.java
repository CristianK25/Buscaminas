package logica.buscaminas;

/**
 *
 * @author windows
 */
public class Casilla {
    private boolean tieneBomba;
    private boolean revelada = false;
    private int [] posicion = new int[2];

    public boolean isTieneBomba() {
        return tieneBomba;
    }

    public void setTieneBomba(boolean tieneBomba) {
        this.tieneBomba = tieneBomba;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }



    public int[] getPosicion() {
        return posicion;
    }
    
}
