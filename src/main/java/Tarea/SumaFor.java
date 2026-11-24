package Tarea;

/** Modelo: calcula la suma mediante un ciclo FOR. */
public class SumaFor implements EstrategiaSuma {
    @Override
    public long calcular(long n) {
        long suma = 0;
        for (long i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }

    @Override public String getNombre() { return "Método FOR"; }
    @Override public String getComplejidad() { return "O(n) - Lineal"; }
    @Override public String getDescripcion() {
        return "Recorre los números desde 1 hasta n y acumula cada valor en una variable.";
    }
}
