/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea4.utils;

import java.util.Optional;

public class Nodo<E> {
    E dato;
    Optional<Nodo<E>> siguiente;
    public Nodo(E dato) {
        this.dato = dato;
        siguiente = Optional.empty();
    }
}
