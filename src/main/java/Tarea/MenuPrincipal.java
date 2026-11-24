package Tarea;

import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        Estilo.configurar(this, "Proyecto IB", Estilo.ANCHO, Estilo.ALTO);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Estilo.FONDO);
        root.add(encabezado(), BorderLayout.NORTH);
        root.add(cuerpo(), BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel encabezado() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Estilo.AZUL);
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton atras = Estilo.boton("←", Estilo.ROJO);
        atras.setPreferredSize(new Dimension(55, 40));
        atras.addActionListener(e -> Navegador.ir(Pantalla.CARATULA));

        JLabel titulo = new JLabel("Proyecto IB", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        header.add(atras, BorderLayout.WEST);
        header.add(titulo, BorderLayout.CENTER);
        header.add(Box.createRigidArea(new Dimension(55, 40)), BorderLayout.EAST);
        return header;
    }

    private JPanel cuerpo() {
        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel sub = new JLabel("Selecciona un tema");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        sub.setForeground(Estilo.AZUL);
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel fila = new JPanel(new GridLayout(1, 4, 20, 0));
        fila.setOpaque(false);
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 330));
        fila.add(new Tarjeta("1", "Eficiencia Algorítmica",
                "Comparación entre el método FOR y la fórmula de Gauss.",
                () -> Navegador.ir(Pantalla.MENU_EFICIENCIA)));
        fila.add(new Tarjeta("2", "Análisis de Complejidad",
                "Reducción polinómica: A ≤p B con un ejercicio precargado.",
                () -> Navegador.ir(Pantalla.COMPLEJIDAD)));
        fila.add(new Tarjeta("3", "P, NP, NP-Hard y NP-Completo",
                "Un ejercicio precargado por cada clase de complejidad.",
                () -> Navegador.ir(Pantalla.CLASES_NP)));
        fila.add(new Tarjeta("4", "Grafos",
                "Orden topológico, Kahn, Prim, Kruskal y Dijkstra.",
                () -> Navegador.ir(Pantalla.GRAFOS)));

        centro.add(sub);
        centro.add(Box.createVerticalStrut(30));
        centro.add(fila);
        centro.add(Box.createVerticalGlue());
        return centro;
    }
}