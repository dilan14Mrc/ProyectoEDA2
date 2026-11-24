package Tarea;

/** Contrato común para los algoritmos que suman los números de 1 hasta n. */
public interface EstrategiaSuma {
    long calcular(long n);
    String getNombre();
    String getComplejidad();
    String getDescripcion();
}
