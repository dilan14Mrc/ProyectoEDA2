package Tarea;

/** Objeto de transferencia con el resultado de una ejecución. */
public class ResultadoCalculo {
    private final long n;
    private final long suma;
    private final long tiempoNanos;
    private final String nombreMetodo;
    private final String complejidad;

    public ResultadoCalculo(long n, long suma, long tiempoNanos,
                            String nombreMetodo, String complejidad) {
        this.n = n;
        this.suma = suma;
        this.tiempoNanos = tiempoNanos;
        this.nombreMetodo = nombreMetodo;
        this.complejidad = complejidad;
    }

    public long getN() { return n; }
    public long getSuma() { return suma; }
    public long getTiempoNanos() { return tiempoNanos; }
    public String getNombreMetodo() { return nombreMetodo; }
    public String getComplejidad() { return complejidad; }
    public double getTiempoMilisegundos() { return tiempoNanos / 1_000_000.0; }
}
