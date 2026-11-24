package Tarea;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Tarjeta extends JPanel {

    private static final Color BORDE = new Color(0xD9DEE8);
    private static final Color GRIS = new Color(0x5B6475);
    private boolean encima = false;

    public Tarjeta(String numero, String titulo, String descripcion, Runnable accion) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(30, 25, 30, 25));

        add(etiqueta(numero, 44, Font.BOLD, Estilo.ROJO));
        add(Box.createVerticalStrut(15));
        add(etiqueta("<html>" + titulo + "</html>", 22, Font.BOLD, Estilo.AZUL));
        add(Box.createVerticalStrut(12));
        add(etiqueta("<html><div style='width:160px'>" + descripcion + "</div></html>", 14, Font.PLAIN, GRIS));
        add(Box.createVerticalGlue());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                encima = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!contains(e.getPoint())) {
                    encima = false;
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                accion.run();
            }
        });
    }

    private JLabel etiqueta(String texto, int tam, int estilo, Color color) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", estilo, tam));
        l.setForeground(color);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(encima ? new Color(0xEEF3FB) : Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(encima ? Estilo.AZUL_CLARO : BORDE);
        g2.setStroke(new BasicStroke(encima ? 2f : 1f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}