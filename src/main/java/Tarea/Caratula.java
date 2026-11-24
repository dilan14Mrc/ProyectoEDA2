package Tarea;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;

public class Caratula extends JFrame {

    public Caratula() {
        Estilo.configurar(this, "Proyecto IB", Estilo.ANCHO, Estilo.ALTO);

        // Fondo con degradado azul
        JPanel fondo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, Estilo.AZUL, getWidth(), getHeight(), Estilo.AZUL_CLARO));
                g2.fillRect(0, 0, getWidth(), getHeight());
                // círculos decorativos suaves
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillOval(getWidth() - 320, -160, 480, 480);
                g2.fillOval(-120, getHeight() - 220, 360, 360);
                // franja roja inferior
                g2.setColor(Estilo.ROJO);
                g2.fillRect(0, getHeight() - 8, getWidth(), 8);
                g2.dispose();
            }
        };
        fondo.setOpaque(true);

        fondo.add(barraSuperior(), BorderLayout.NORTH);
        fondo.add(centro(), BorderLayout.CENTER);
        fondo.add(pie(), BorderLayout.SOUTH);

        setContentPane(fondo);
    }

    // Logo arriba a la izquierda
    private JComponent barraSuperior() {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        barra.setOpaque(false);
        barra.setBorder(BorderFactory.createEmptyBorder(18, 24, 0, 24));

        JPanel chip = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                g2.dispose();
            }
        };
        chip.setOpaque(false);
        chip.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        java.net.URL url = getClass().getResource("/epn_logo.png");
        JLabel lbl;
        if (url != null) {
            Image img = new ImageIcon(url).getImage().getScaledInstance(-1, 90, Image.SCALE_SMOOTH);
            lbl = new JLabel(new ImageIcon(img));
        } else {
            lbl = new JLabel("EPN");
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
            lbl.setForeground(Estilo.AZUL);
        }
        chip.add(lbl);
        barra.add(chip);
        return barra;
    }

    // Títulos centrados
    private JComponent centro() {
        JPanel c = new JPanel();
        c.setOpaque(false);
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        c.add(Box.createVerticalGlue());
        c.add(texto("ESCUELA POLITÉCNICA NACIONAL", 14, Font.BOLD, new Color(0xFFB3BD), 4f));
        c.add(Box.createVerticalStrut(14));
        c.add(texto("Estructura de Datos y Algoritmos II", 30, Font.PLAIN, Color.WHITE, 0f));
        c.add(Box.createVerticalStrut(6));
        c.add(texto("Proyecto IB", 72, Font.BOLD, Color.WHITE, 0f));
        c.add(Box.createVerticalStrut(10));

        JPanel linea = new JPanel();
        linea.setBackground(Estilo.ROJO);
        linea.setMaximumSize(new Dimension(120, 5));
        linea.setPreferredSize(new Dimension(120, 5));
        linea.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.add(linea);

        c.add(Box.createVerticalStrut(26));
        c.add(texto("Dilan Simbaña   •   GR2SI", 20, Font.BOLD, Color.WHITE, 0f));
        c.add(Box.createVerticalStrut(8));
        c.add(texto("Profesor: Gustavo Guaigua", 17, Font.PLAIN, new Color(0xD6E2F5), 0f));
        c.add(Box.createVerticalGlue());
        return c;
    }

    // Botón siempre visible abajo
    private JComponent pie() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JButton empezar = Estilo.boton("EMPEZAR  →", Estilo.ROJO);
        empezar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        empezar.setPreferredSize(new Dimension(300, 58));
        empezar.addActionListener(e -> Navegador.ir(Pantalla.MENU_PRINCIPAL));
        p.add(empezar);
        return p;
    }

    private JLabel texto(String t, int tam, int estilo, Color color, float espaciado) {
        JLabel l = new JLabel(t, SwingConstants.CENTER);
        Font f = new Font("Segoe UI", estilo, tam);
        if (espaciado > 0) {
            f = f.deriveFont(Map.of(TextAttribute.TRACKING, espaciado / tam));
        }
        l.setFont(f);
        l.setForeground(color);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }
}