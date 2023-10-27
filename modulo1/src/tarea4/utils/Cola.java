package tarea4.utils;

import tarea4.utils.ListaEnlazada;

import java.util.Optional;

/**
 * Autor: Eduardo Oroxco Osuna
 */
public class Cola<E> {
    private ListaEnlazada<E> cola;

    public Cola() {
        cola = new ListaEnlazada<>();
    }

    public void encolar(E elementoAEncolar) {
        cola.agregar(elementoAEncolar);
    }

    public Optional<E> desencolar() {
        Optional<E> desencolado = cola.obtener(0);
        cola.eliminar(desencolado.get());
        return desencolado;
    }

    public Optional<E> frente() {
        return cola.obtener(0);
    }

    public int tamano() {
        return cola.obtenerTamaño();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }

    public Cola<E> invertir() {
        if (!estaVacia()) {
            Cola<E> colaInvertida = this;
            Pila<E> pilaAuxiliar = new Pila<>();

            while (!colaInvertida.estaVacia()) {
                pilaAuxiliar.empujar(colaInvertida.desencolar().get());
            }

            while (!pilaAuxiliar.estáVacía()) {
                colaInvertida.encolar(pilaAuxiliar.pop().get());
            }

            return colaInvertida;
        }

        return this;
    }
}
