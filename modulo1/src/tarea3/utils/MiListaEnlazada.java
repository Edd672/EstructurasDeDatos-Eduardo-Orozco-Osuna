/**
 * @autor: Eduardo Orozco Osuna
 */

package tarea3.utils;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Esta clase representa una implementación de una lista enlazada simple.
 * @param <T> El tipo de elementos almacenados en la lista enlazada.
 */
public class MiListaEnlazada<T> implements IListaEnlazada<T> {
    private Optional<Node<T>> raiz; // Representa el primer nodo en la lista enlazada
    private int tamano; // Almacena el número de elementos en la lista enlazada

    /**
     * Construye una lista enlazada vacía.
     */
    public MiListaEnlazada() {
        raiz = Optional.empty();
        tamano = 0;
    }

    /**
     * Obtiene el elemento en el índice especificado en la lista enlazada.
     *
     * @param indice El índice del elemento a obtener.
     * @return Un Optional que contiene el elemento en el índice dado, o un Optional vacío si el índice está fuera de límites.
     * @throws IndexOutOfBoundsException Si el índice es negativo o mayor o igual al tamaño de la lista.
     */
    @Override
    public Optional<T> get(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException();
        }

        int indiceActual = 0;
        Optional<Node<T>> nodoActual = raiz;

        while (indiceActual != indice) {
            nodoActual = nodoActual.get().next;
            ++indiceActual;
        }

        return Optional.of(nodoActual.get().data);
    }

    /**
     * Agrega un elemento al final de la lista enlazada.
     *
     * @param elemento El elemento a agregar.
     */
    @Override
    public void add(T elemento) {
        Node<T> nuevoNodo = new Node<>(elemento);

        if (raiz.isEmpty()) {
            raiz = Optional.of(nuevoNodo);
        } else {
            Optional<Node<T>> nodoActual = raiz;

            while (nodoActual.get().next.isPresent()) {
                nodoActual = nodoActual.get().next;
            }

            nodoActual.get().next = Optional.of(nuevoNodo);
        }

        incrementarTamano();
    }

    /**
     * Agrega un elemento al principio de la lista enlazada.
     *
     * @param elemento El elemento a agregar.
     */
    public void agregarPrimero(T elemento) {
        Node<T> nuevoNodo = new Node<>(elemento);

        if (raiz.isEmpty()) {
            raiz = Optional.of(nuevoNodo);
        } else {
            nuevoNodo.next = raiz;
            raiz = Optional.of(nuevoNodo);
        }

        incrementarTamano();
    }

    /**
     * Actualiza el valor de un elemento en la lista enlazada.
     *
     * @param valorAntiguo El valor que se actualizará.
     * @param valorNuevo El nuevo valor para reemplazar el valor antiguo.
     * @throws NoSuchElementException Si no se encuentra el valor antiguo en la lista enlazada.
     */
    @Override
    public void update(T valorAntiguo, T valorNuevo) {
        Optional<Node<T>> nodoActual = raiz;

        while (nodoActual.isPresent() && !nodoActual.get().data.equals(valorAntiguo)) {
            nodoActual = nodoActual.get().next;
        }

        if (nodoActual.isPresent()) {
            nodoActual.get().data = valorNuevo;
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
    public void remove(T elemento) {
        if (raiz.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (raiz.get().data.equals(elemento)) {
            raiz = raiz.get().next;
            reducirTamano();
            return;
        }

        Optional<Node<T>> nodoActual = raiz;
        Optional<Node<T>> nodoPrevio = Optional.empty();

        while (nodoActual.isPresent()) {
            if (nodoActual.get().data.equals(elemento)) {
                nodoPrevio.get().next = nodoActual.get().next;
                reducirTamano();
                return;
            }
            nodoPrevio = nodoActual;
            nodoActual = nodoActual.get().next;
        }

        throw new NoSuchElementException();
    }

    /**
     * Obtiene el número de elementos en la lista enlazada.
     *
     * @return El número de elementos en la lista enlazada.
     */
    @Override
    public int getSize() {
        return tamano;
    }

    /**
     * Incrementa el tamaño de la lista enlazada en 1.
     */
    private void incrementarTamano() {
        ++tamano;
    }

    /**
     * Reduce el tamaño de la lista enlazada en 1.
     */
    private void reducirTamano() {
        --tamano;
    }

    /**
     * Verifica si la lista enlazada contiene un elemento específico.
     *
     * @param t El elemento a verificar.
     * @return True si el elemento se encuentra en la lista enlazada, de lo contrario, false.
     */
    @Override
    public boolean contains(T t) {
        Optional<Node<T>> nodoActual = raiz;

        while (!nodoActual.get().data.equals(t) && nodoActual.get().next.isPresent()) {
            nodoActual = nodoActual.get().next;
        }

        if (nodoActual.get().data.equals(t)) {
            return true;
        }
        return false;
    }
}
