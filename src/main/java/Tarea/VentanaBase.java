package Tarea;

import java.awt.*;
import javax.swing.*;

/** Vista reutilizable para mostrar la ejecución de un algoritmo de suma. */
public abstract class VentanaBase extends JFrame {
    private final JTextField campo = new JTextField();
    private final JLabel lblEstado = new JLabel("Ingresa un valor para comenzar.");
    private final JLabel lblN = new JLabel("—");
    private final JLabel lblSuma = new JLabel("—");
    private final JLabel lblTiempo = new JLabel("—");
    private final JLabel lblComplejidad = new JLabel("—");
    private final EficienciaControlador controlador = new EficienciaControlador();
    private final EstrategiaSuma estrategia;

    protected VentanaBase(String titulo, Color acento, EstrategiaSuma estrategia) {
        this.estrategia = estrategia;
        Estilo.configurar(this, titulo, Estilo.ANCHO, Estilo.ALTO);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(Estilo.FONDO);
        root.add(crearEncabezado(titulo), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new GridLayout(1, 2, 24, 0));
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));
        contenido.add(crearPanelEntrada(acento));
        contenido.add(crearPanelResultado(acento));
        root.add(contenido, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel crearEncabezado(String titulo) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Estilo.AZUL);
        header.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));
        JButton atras = Estilo.boton("←", Estilo.ROJO);
        atras.setPreferredSize(new Dimension(55, 42));
        atras.addActionListener(e -> Navegador.ir(Pantalla.MENU_EFICIENCIA));
        JLabel texto = new JLabel(titulo, SwingConstants.CENTER);
        texto.setFont(new Font("Segoe UI", Font.BOLD, 23));
        texto.setForeground(Color.WHITE);
        header.add(atras, BorderLayout.WEST);
        header.add(texto, BorderLayout.CENTER);
        header.add(Box.createRigidArea(new Dimension(55, 42)), BorderLayout.EAST);
        return header;
    }

    private JPanel crearPanelEntrada(Color acento) {
        JPanel panel = tarjeta();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiqueta("ENTRADA", 13, Font.BOLD, Estilo.AZUL_CLARO));
        panel.add(Box.createVerticalStrut(18));
        panel.add(etiqueta(estrategia.getNombre(), 25, Font.BOLD, Estilo.AZUL));
        panel.add(Box.createVerticalStrut(14));
        panel.add(etiquetaHtml(estrategia.getDescripcion(), 15, Estilo.GRIS));
        panel.add(Box.createVerticalStrut(28));
        panel.add(etiqueta("Cantidad de números (n)", 16, Font.BOLD, Estilo.AZUL));
        panel.add(Box.createVerticalStrut(10));

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(acento, 2),
                BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        panel.add(campo);
        panel.add(Box.createVerticalStrut(12));
        panel.add(etiqueta("Rango permitido: 1 a " + EficienciaControlador.MAXIMO_N,
                12, Font.PLAIN, Estilo.GRIS));
        panel.add(Box.createVerticalStrut(22));

        JButton calcular = Estilo.boton("CALCULAR", acento);
        calcular.setAlignmentX(Component.LEFT_ALIGNMENT);
        calcular.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        calcular.setPreferredSize(new Dimension(300, 50));
        calcular.addActionListener(e -> ejecutar());
        campo.addActionListener(e -> ejecutar());
        panel.add(calcular);
        panel.add(Box.createVerticalStrut(18));
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEstado.setForeground(Estilo.GRIS);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblEstado);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel crearPanelResultado(Color acento) {
        JPanel panel = tarjeta();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiqueta("RESULTADOS", 13, Font.BOLD, Estilo.AZUL_CLARO));
        panel.add(Box.createVerticalStrut(22));
        agregarDato(panel, "Valor ingresado", lblN);
        agregarDato(panel, "Resultado de la suma", lblSuma);
        agregarDato(panel, "Tiempo de ejecución", lblTiempo);
        agregarDato(panel, "Complejidad temporal", lblComplejidad);
        panel.add(Box.createVerticalGlue());
        JLabel nota = etiquetaHtml("El tiempo puede variar según el equipo, la carga del sistema y la JVM. "
                + "La complejidad describe el crecimiento teórico del algoritmo.", 12, Estilo.GRIS);
        panel.add(nota);
        return panel;
    }

    private void agregarDato(JPanel panel, String titulo, JLabel valor) {
        panel.add(etiqueta(titulo, 13, Font.PLAIN, Estilo.GRIS));
        panel.add(Box.createVerticalStrut(4));
        valor.setFont(new Font("Segoe UI", Font.BOLD, 21));
        valor.setForeground(Estilo.AZUL);
        valor.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(valor);
        panel.add(Box.createVerticalStrut(19));
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

    private JLabel etiquetaHtml(String texto, int tam, Color color) {
        return etiqueta("<html><div style='width:360px'>" + texto + "</div></html>",
                tam, Font.PLAIN, color);
    }

    private void ejecutar() {
        try {
            ResultadoCalculo r = controlador.calcular(campo.getText(), estrategia);
            lblN.setText(String.format("%,d", r.getN()));
            lblSuma.setText(String.format("%,d", r.getSuma()));
            lblTiempo.setText(String.format("%.6f ms", r.getTiempoMilisegundos()));
            lblComplejidad.setText(r.getComplejidad());
            lblEstado.setForeground(new Color(0x247A45));
            lblEstado.setText("Cálculo realizado correctamente.");
        } catch (IllegalArgumentException ex) {
            lblEstado.setForeground(Estilo.ROJO);
            lblEstado.setText(ex.getMessage());
        }
    }
}
