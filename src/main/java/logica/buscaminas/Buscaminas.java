package logica.buscaminas;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import logica.buscaminas.vista.VentanaBuscaminas;
/**
 *
 * @author windows
 */
public class Buscaminas {

    public static void main(String[] args) {
        FlatCyanLightIJTheme.setup();
        VentanaBuscaminas v1 = new VentanaBuscaminas();
        v1.setVisible(true);
    }
}
