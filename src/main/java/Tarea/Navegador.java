package Tarea;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class Navegador {

    private static final Map<Pantalla, Supplier<JFrame>> rutas = new EnumMap<>(Pantalla.class);
    private static JFrame actual;

    private Navegador() {
    }

    public static void registrar(Pantalla pantalla, Supplier<JFrame> fabrica) {
        rutas.put(pantalla, fabrica);
    }

    public static void ir(Pantalla pantalla) {
        Supplier<JFrame> fabrica = rutas.get(pantalla);
        if (fabrica == null) {
            JOptionPane.showMessageDialog(actual, "Esta sección estará disponible pronto.");
            return;
        }
        JFrame nueva = fabrica.get();
        nueva.setVisible(true);
        if (actual != null) {
            actual.dispose();
        }
        actual = nueva;
    }
}