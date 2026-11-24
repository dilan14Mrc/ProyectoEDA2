package Tarea;

/** Contiene los resultados medidos de FOR y Gauss para el mismo n. */
public class ResultadoComparacion {
    private final long n;
    private final ResultadoCalculo resultadoFor;
    private final ResultadoCalculo resultadoGauss;

    public ResultadoComparacion(long n, ResultadoCalculo resultadoFor,
                                ResultadoCalculo resultadoGauss) {
        this.n = n;
        this.resultadoFor = resultadoFor;
        this.resultadoGauss = resultadoGauss;
    }

    public long getN() { return n; }
    public ResultadoCalculo getResultadoFor() { return resultadoFor; }
    public ResultadoCalculo getResultadoGauss() { return resultadoGauss; }

    public String getMetodoMasRapido() {
        if (resultadoFor.getTiempoNanos() == resultadoGauss.getTiempoNanos()) return "Empate técnico";
        return resultadoFor.getTiempoNanos() < resultadoGauss.getTiempoNanos()
                ? "FOR" : "GAUSS";
    }

    public double getDiferenciaMilisegundos() {
        return Math.abs(resultadoFor.getTiempoMilisegundos()
                - resultadoGauss.getTiempoMilisegundos());
    }
}
