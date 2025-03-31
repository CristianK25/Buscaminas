package logica.buscaminas;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import logica.buscaminas.vista.VentanaBuscaminas;
/**
 *
 * @author windows
 */
public class Buscaminas {

    public static void main(String[] args) {
        FlatCarbonIJTheme.setup();
        VentanaBuscaminas v1 = new VentanaBuscaminas();
        v1.setLocationRelativeTo(null);
        v1.setVisible(true);
    }
}
