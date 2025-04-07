package logica.buscaminas;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import javax.swing.UIManager;
import logica.buscaminas.vista.VentanaBuscaminas;
/**
 *
 * @author windows
 */
public class Buscaminas {

    public static void main(String[] args) {
        FlatCyanLightIJTheme.setup();
        UIManager.put("Button.foreground", null); // Permite setForeground() personalizado
        UIManager.put("Button.disabledForeground", null); // Evita gris al deshabilitar
        VentanaBuscaminas v1 = new VentanaBuscaminas();
        v1.setVisible(true);
    }
}
