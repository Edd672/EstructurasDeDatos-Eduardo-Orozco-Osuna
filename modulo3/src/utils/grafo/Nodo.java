/**
 * @author Eduardo Orozco Osuna
 */

package utils.grafo;

/**
 * Representa un nodo en un árbol de búsqueda binaria.
 *
 * @param <T> El tipo de valores almacenados en el árbol.
 */
class Nodo<T extends Comparable<T>> {
    T valor;
    Nodo<T> izquierda, derecha;

    /**
     * Constructor de la clase Nodo.
     *
     * @param elemento El elemento que se va a almacenar en el nodo.
     */
    public Nodo(T elemento) {
        valor = elemento;
        izquierda = derecha = null;
    }
}
