package Tarea;

import java.awt.*;
import javax.swing.*;

public class MenuEficiencia extends JFrame {

    public MenuEficiencia() {
        Estilo.configurar(this, "Eficiencia Algorítmica", Estilo.ANCHO, Estilo.ALTO);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Estilo.FONDO);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Estilo.AZUL);
        header.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton atras = Estilo.boton("←", Estilo.ROJO);
        atras.setPreferredSize(new Dimension(55, 40));
        atras.addActionListener(e -> {
            Navegador.ir(Pantalla.MENU_PRINCIPAL);
        });
        JLabel titulo = new JLabel("Eficiencia Algorítmica", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        header.add(atras, BorderLayout.WEST);
        header.add(titulo, BorderLayout.CENTER);
        header.add(Box.createRigidArea(new Dimension(55, 40)), BorderLayout.EAST);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(80, 60, 40, 60));

        JLabel sub = new JLabel("Elige el método para sumar de 1 a n", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setForeground(Estilo.AZUL);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton bFor = Estilo.boton("MÉTODO FOR", Estilo.AZUL);
        JButton bGauss = Estilo.boton("MÉTODO GAUSS", Estilo.ROJO);
        JButton bComp = Estilo.boton("COMPARACIÓN", Estilo.AZUL_CLARO);
        for (JButton b : new JButton[]{bFor, bGauss, bComp}) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(340, 70));
            b.setPreferredSize(new Dimension(340, 70));
            b.setFont(new Font("Segoe UI", Font.BOLD, 20));
        }
        bFor.addActionListener(e -> {
            Navegador.ir(Pantalla.EFICIENCIA_FOR);
        });
        bGauss.addActionListener(e -> {
            Navegador.ir(Pantalla.EFICIENCIA_GAUSS);
        });
        bComp.addActionListener(e -> {
            Navegador.ir(Pantalla.EFICIENCIA_COMPARACION);
        });

        centro.add(sub);
        centro.add(Box.createVerticalStrut(50));
        centro.add(bFor);
        centro.add(Box.createVerticalStrut(30));
        centro.add(bGauss);
        centro.add(Box.createVerticalStrut(30));
        centro.add(bComp);

        root.add(header, BorderLayout.NORTH);
        root.add(centro, BorderLayout.CENTER);
        setContentPane(root);
    }
}
