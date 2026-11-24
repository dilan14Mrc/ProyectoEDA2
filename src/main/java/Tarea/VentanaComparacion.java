package Tarea;

import java.awt.*;
import javax.swing.*;

/** Vista para comparar ambos algoritmos con el mismo valor de entrada. */
public class VentanaComparacion extends JFrame {
    private final JTextField campo = new JTextField();
    private final JLabel lblEstado = new JLabel("Ingresa un valor para comparar ambos métodos.");
    private final JLabel lblFor = new JLabel("—");
    private final JLabel lblGauss = new JLabel("—");
    private final JLabel lblGanador = new JLabel("—");
    private final JLabel lblDiferencia = new JLabel("—");
    private final EficienciaControlador controlador = new EficienciaControlador();

    public VentanaComparacion() {
        Estilo.configurar(this, "Comparación FOR vs GAUSS", Estilo.ANCHO, Estilo.ALTO);
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Estilo.FONDO);
        root.add(crearEncabezado(), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new GridLayout(1, 2, 24, 0));
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));
        contenido.add(crearEntrada());
        contenido.add(crearResultados());
        root.add(contenido, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel crearEncabezado() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Estilo.AZUL);
        header.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));
        JButton atras = Estilo.boton("←", Estilo.ROJO);
        atras.setPreferredSize(new Dimension(55, 42));
        atras.addActionListener(e -> Navegador.ir(Pantalla.MENU_EFICIENCIA));
        JLabel titulo = new JLabel("Comparación FOR vs GAUSS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 23));
        titulo.setForeground(Color.WHITE);
        header.add(atras, BorderLayout.WEST);
        header.add(titulo, BorderLayout.CENTER);
        header.add(Box.createRigidArea(new Dimension(55, 42)), BorderLayout.EAST);
        return header;
    }

    private JPanel crearEntrada() {
        JPanel p = tarjeta();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(etiqueta("COMPARACIÓN DE ALGORITMOS", 13, Font.BOLD, Estilo.AZUL_CLARO));
        p.add(Box.createVerticalStrut(20));
        p.add(etiqueta("¿Cuál método resuelve la suma más rápido?", 23, Font.BOLD, Estilo.AZUL));
        p.add(Box.createVerticalStrut(14));
        p.add(etiquetaHtml("Ambos métodos calcularán la suma de 1 hasta n. "
                + "Se medirán varias ejecuciones y se utilizará la mediana para reducir variaciones.", 15));
        p.add(Box.createVerticalStrut(30));
        p.add(etiqueta("Cantidad de números (n)", 16, Font.BOLD, Estilo.AZUL));
        p.add(Box.createVerticalStrut(10));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Estilo.AZUL_CLARO, 2),
                BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        p.add(campo);
        p.add(Box.createVerticalStrut(12));
        p.add(etiqueta("Rango permitido: 1 a " + EficienciaControlador.MAXIMO_N, 12, Font.PLAIN, Estilo.GRIS));
        p.add(Box.createVerticalStrut(22));
        JButton comparar = Estilo.boton("COMPARAR MÉTODOS", Estilo.AZUL_CLARO);
        comparar.setAlignmentX(Component.LEFT_ALIGNMENT);
        comparar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        comparar.addActionListener(e -> ejecutar());
        campo.addActionListener(e -> ejecutar());
        p.add(comparar);
        p.add(Box.createVerticalStrut(18));
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEstado.setForeground(Estilo.GRIS);
        p.add(lblEstado);
        p.add(Box.createVerticalGlue());
        return p;
    }

    private JPanel crearResultados() {
        JPanel p = tarjeta();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(etiqueta("RESULTADOS DE LA PRUEBA", 13, Font.BOLD, Estilo.AZUL_CLARO));
        p.add(Box.createVerticalStrut(22));
        agregarResultado(p, "Tiempo FOR · O(n)", lblFor);
        agregarResultado(p, "Tiempo Gauss · O(1)", lblGauss);
        p.add(etiqueta("Conclusión de esta ejecución", 13, Font.PLAIN, Estilo.GRIS));
        lblGanador.setFont(new Font("Segoe UI", Font.BOLD, 23));
        lblGanador.setForeground(Estilo.ROJO);
        lblGanador.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblGanador);
        p.add(Box.createVerticalStrut(12));
        agregarResultado(p, "Diferencia de tiempo", lblDiferencia);
        p.add(Box.createVerticalGlue());
        p.add(etiquetaHtml("Importante: los tiempos son mediciones experimentales y pueden variar. "
                + "La comparación teórica es FOR: O(n), Gauss: O(1).", 12));
        return p;
    }

    private void agregarResultado(JPanel p, String titulo, JLabel valor) {
        p.add(etiqueta(titulo, 13, Font.PLAIN, Estilo.GRIS));
        p.add(Box.createVerticalStrut(4));
        valor.setFont(new Font("Segoe UI", Font.BOLD, 21));
        valor.setForeground(Estilo.AZUL);
        valor.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(valor);
        p.add(Box.createVerticalStrut(20));
    }

    private JPanel tarjeta() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0xDDE3EC)),
                BorderFactory.createEmptyBorder(28, 30, 28, 30)));
        return p;
    }

    private JLabel etiqueta(String texto, int tam, int estilo, Color color) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", estilo, tam));
        l.setForeground(color);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel etiquetaHtml(String texto, int tam) {
        return etiqueta("<html><div style='width:360px'>" + texto + "</div></html>",
                tam, Font.PLAIN, Estilo.GRIS);
    }

    private void ejecutar() {
        try {
            ResultadoComparacion r = controlador.comparar(campo.getText());
            lblFor.setText(String.format("%.6f ms", r.getResultadoFor().getTiempoMilisegundos()));
            lblGauss.setText(String.format("%.6f ms", r.getResultadoGauss().getTiempoMilisegundos()));
            lblGanador.setText(r.getMetodoMasRapido());
            lblDiferencia.setText(String.format("%.6f ms", r.getDiferenciaMilisegundos()));
            lblEstado.setForeground(new Color(0x247A45));
            lblEstado.setText("Comparación completada para n = " + r.getN());
        } catch (IllegalArgumentException ex) {
            lblEstado.setForeground(Estilo.ROJO);
            lblEstado.setText(ex.getMessage());
        }
    }
}
