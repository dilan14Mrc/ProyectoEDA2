package Tarea;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Navegador.registrar(Pantalla.CARATULA, Caratula::new);
            Navegador.registrar(Pantalla.MENU_PRINCIPAL, MenuPrincipal::new);
            Navegador.registrar(Pantalla.MENU_EFICIENCIA, MenuEficiencia::new);
            Navegador.registrar(Pantalla.EFICIENCIA_FOR, VentanaFor::new);
            Navegador.registrar(Pantalla.EFICIENCIA_GAUSS, VentanaGauss::new);
            Navegador.registrar(Pantalla.EFICIENCIA_COMPARACION, VentanaComparacion::new);
            Navegador.ir(Pantalla.CARATULA);
        });
    }
}