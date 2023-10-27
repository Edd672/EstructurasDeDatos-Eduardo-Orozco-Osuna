package tarea4.ejercicio_3.process;

import tarea4.utils.Cola;

import java.util.Optional;
import java.util.Random;

public class SimuladorServidor {
    private Random aleatorio;

    public static int obtenerNumeroDeProcesos() {
        return 60;
    }

    public static <E> void cargarServidor(Cola<E> servidor, E dato) {
        servidor.encolar(dato);
    }

    public static <E> Optional<E> procesarProceso(Cola<E> servidorA, Cola<E> servidorB, double probabilidad) {
        if (probabilidad <= 0.3) {
            if (!servidorA.estaVacia()) {
                return servidorA.desencolar();
            }
        }
        if (!servidorB.estaVacia()) {
            return servidorB.desencolar();
        }
        return null;
    }
}
