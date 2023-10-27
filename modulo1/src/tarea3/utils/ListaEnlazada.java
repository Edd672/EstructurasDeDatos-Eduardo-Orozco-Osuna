/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea3.utils;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Esta clase representa una implementación de una lista enlazada simple.
 * @param <E> El tipo de elementos almacenados en la lista enlazada.
 */
public class ListaEnlazada<E> implements IListaEnlazada<E> {
    private Optional<Nodo<E>> primero; // Representa el primer nodo de la lista enlazada
    private int tamaño; // Almacena el número de elementos en la lista enlazada

    /**
     * Construye una lista enlazada vacía.
     */
    public ListaEnlazada() {
        primero = Optional.empty();
        tamaño = 0;
    }

    /**
     * Obtiene el elemento en el índice especificado en la lista enlazada.
     *
     * @param índice El índice del elemento a obtener.
     * @return Un Optional que contiene el elemento en el índice dado, o un Optional vacío si el índice está fuera de los límites.
     * @throws IndexOutOfBoundsException Si el índice es negativo o mayor o igual que el tamaño de la lista.
     */
    @Override
    public Optional<E> obtener(int índice) {
        if (índice < 0 || índice >= tamaño) {
            throw new IndexOutOfBoundsException();
        }

        int índiceActual = 0;
        Optional<Nodo<E>> nodoActual = primero;

        while (índiceActual != índice) {
            nodoActual = nodoActual.get().siguiente;
            ++índiceActual;
        }

        return Optional.of(nodoActual.get().dato);
    }

    /**
     * Agrega un elemento al final de la lista enlazada.
     *
     * @param elemento El elemento a agregar.
     */
    @Override
    public void agregar(E elemento) {
        Nodo<E> nuevoNodo = new Nodo<>(elemento);

        if (primero.isEmpty()) {
            primero = Optional.of(nuevoNodo);
        } else {
            Optional<Nodo<E>> nodoActual = primero;

            while (nodoActual.get().siguiente.isPresent()) {
                nodoActual = nodoActual.get().siguiente;
            }

            nodoActual.get().siguiente = Optional.of(nuevoNodo);
        }

        incrementarTamaño();
    }

    /**
     * Agrega un elemento al principio de la lista enlazada.
     *
     * @param elemento El elemento a agregar.
     */
    public void agregarPrimero(E elemento) {
        Nodo<E> nuevoNodo = new Nodo<>(elemento);

        if (primero.isEmpty()) {
            primero = Optional.of(nuevoNodo);
        } else {
            nuevoNodo.siguiente = primero;
            primero = Optional.of(nuevoNodo);
        }

        incrementarTamaño();
    }

    /**
     * Actualiza el valor de un elemento en la lista enlazada.
     *
     * @param valorAntiguo El valor a actualizar.
     * @param nuevoValor El nuevo valor que reemplazará al valor antiguo.
     * @throws NoSuchElementException Si el valor antiguo no se encuentra en la lista enlazada.
     */
    @Override
    public void actualizar(E valorAntiguo, E nuevoValor) {
        Optional<Nodo<E>> nodoActual = primero;

        while (nodoActual.isPresent() && !nodoActual.get().dato.equals(valorAntiguo)) {
            nodoActual = nodoActual.get().siguiente;
        }

        if (nodoActual.isPresent()) {
            nodoActual.get().dato = nuevoValor;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Elimina un elemento de la lista enlazada.
     *
     * @param elemento El elemento a eliminar.
     * @throws NoSuchElementException Si el elemento no se encuentra en la lista enlazada.
     */
    @Override
    public void eliminar(E elemento) {
        if (primero.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (primero.get().dato.equals(elemento)) {
            primero = primero.get().siguiente;
            disminuirTamaño();
            return;
        }

        Optional<Nodo<E>> nodoActual = primero;
        Optional<Nodo<E>> nodoAnterior = Optional.empty();

        while (nodoActual.isPresent()) {
            if (nodoActual.get().dato.equals(elemento)) {
                nodoAnterior.get().siguiente = nodoActual.get().siguiente;
                disminuirTamaño();
                return;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.get().siguiente;
        }

        throw new NoSuchElementException();
    }

    /**
     * Obtiene el número de elementos en la lista enlazada.
     *
     * @return El número de elementos en la lista enlazada.
     */
    @Override
    public int obtenerTamaño() {
        return tamaño;
    }

    /**
     * Incrementa el tamaño de la lista enlazada en 1.
     */
    private void incrementarTamaño() {
        ++tamaño;
    }

    /**
     * Disminuye el tamaño de la lista enlazada en 1.
     */
    private void disminuirTamaño() {
        --tamaño;
    }

    /**
     * Verifica si la lista enlazada contiene un elemento específico.
     *
     * @param elemento El elemento a buscar.
     * @return True si se encuentra el elemento en la lista enlazada, de lo contrario, false.
     */
    @Override
    public boolean contiene(E elemento) {
        Optional<Nodo<E>> nodoActual = primero;

        while (!nodoActual.get().dato.equals(elemento) && nodoActual.get().siguiente.isPresent()) {
            nodoActual = nodoActual.get().siguiente;
        }

        if (nodoActual.get().dato.equals(elemento)) {
            return true;
        }
        return false;
    }
}
