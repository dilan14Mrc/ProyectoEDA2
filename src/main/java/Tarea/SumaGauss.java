package Tarea;

/** Modelo: calcula la suma mediante la fórmula de Gauss. */
public class SumaGauss implements EstrategiaSuma {
    @Override
    public long calcular(long n) {
        return n * (n + 1) / 2;
    }

    @Override public String getNombre() { return "Método Gauss"; }
    @Override public String getComplejidad() { return "O(1) - Constante"; }
    @Override public String getDescripcion() {
        return "Aplica directamente la fórmula n × (n + 1) / 2, sin recorrer los números.";
    }
}
