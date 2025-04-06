package logica.buscaminas;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import logica.buscaminas.vista.VentanaBuscaminas;
/**
 *
 * @author windows
 */
public class Buscaminas {

    public static void main(String[] args) {
        FlatHiberbeeDarkIJTheme.setup();
        VentanaBuscaminas v1 = new VentanaBuscaminas();
        v1.setVisible(true);
    }
}
