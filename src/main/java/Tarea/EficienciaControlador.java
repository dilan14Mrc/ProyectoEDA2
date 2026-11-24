package Tarea;

import java.util.Arrays;

/** Controlador: valida los datos y coordina las ejecuciones del modelo. */
public class EficienciaControlador {
    public static final long MAXIMO_N = 5_000_000L;
    private static final int REPETICIONES = 5;

    public ResultadoCalculo calcular(String entrada, EstrategiaSuma estrategia) {
        long n = validarEntrada(entrada);
        long inicio = System.nanoTime();
        long suma = estrategia.calcular(n);
        long tiempo = System.nanoTime() - inicio;
        return new ResultadoCalculo(n, suma, tiempo,
                estrategia.getNombre(), estrategia.getComplejidad());
    }

    public ResultadoComparacion comparar(String entrada) {
        long n = validarEntrada(entrada);
        EstrategiaSuma forMetodo = new SumaFor();
        EstrategiaSuma gaussMetodo = new SumaGauss();

        // Calentamiento básico de la JVM: reduce parte del efecto de la primera ejecución.
        forMetodo.calcular(Math.min(n, 1_000));
        gaussMetodo.calcular(Math.min(n, 1_000));

        long tiempoFor = medirMediana(forMetodo, n);
        long tiempoGauss = medirMediana(gaussMetodo, n);

        long sumaFor = forMetodo.calcular(n);
        long sumaGauss = gaussMetodo.calcular(n);
        ResultadoCalculo rFor = new ResultadoCalculo(n, sumaFor, tiempoFor,
                forMetodo.getNombre(), forMetodo.getComplejidad());
        ResultadoCalculo rGauss = new ResultadoCalculo(n, sumaGauss, tiempoGauss,
                gaussMetodo.getNombre(), gaussMetodo.getComplejidad());
        return new ResultadoComparacion(n, rFor, rGauss);
    }

    private long medirMediana(EstrategiaSuma estrategia, long n) {
        long[] tiempos = new long[REPETICIONES];
        for (int i = 0; i < REPETICIONES; i++) {
            long inicio = System.nanoTime();
            estrategia.calcular(n);
            tiempos[i] = System.nanoTime() - inicio;
        }
        Arrays.sort(tiempos);
        return tiempos[REPETICIONES / 2];
    }

    private long validarEntrada(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingresa un número entero.");
        }
        final long n;
        try {
            n = Long.parseLong(entrada.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El valor debe ser un número entero válido.");
        }
        if (n < 1 || n > MAXIMO_N) {
            throw new IllegalArgumentException("Ingresa un entero entre 1 y " + MAXIMO_N + ".");
        }
        return n;
    }
}
