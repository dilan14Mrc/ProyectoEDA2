package Tarea;

import java.awt.*;
import javax.swing.*;

public class Estilo {
    public static final Color AZUL = new Color(0x0B2A5B);
    public static final Color AZUL_CLARO = new Color(0x1F4E9C);
    public static final Color ROJO = new Color(0xC8102E);
    public static final Color FONDO = new Color(0xF4F6FA);
    public static final int ANCHO = 1100;
    public static final int ALTO = 680;
    public static final Color GRIS = new Color(0x5B6475);

    public static JButton boton(String texto, Color fondo) {
        JButton b = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c = getBackground();
                if (getModel().isPressed()) c = c.darker();
                else if (getModel().isRollover()) c = c.brighter();
                g2.setColor(c);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setBackground(fondo);
        
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setRolloverEnabled(true);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static void configurar(JFrame f, String titulo, int w, int h) {
        f.setTitle(titulo);
        // Si la pantalla es más pequeña (por la escala de Windows), la ventana se ajusta
        Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        f.setSize(Math.min(w, area.width), Math.min(h, area.height));
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
