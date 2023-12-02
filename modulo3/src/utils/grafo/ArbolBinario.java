/**
 * Clase de implementación de un Árbol de Búsqueda Binaria.
 *
 * @param <T> El tipo de valores almacenados en el árbol.
 */

package utils.grafo;

public class ArbolBinario<T extends Comparable<T>> {
    Nodo<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    /**
     * Inserta un nuevo valor en el árbol.
     *
     * @param valor El valor a insertar.
     */
    public void agregar(T valor) {
        raiz = agregarEnSubArbol(raiz, valor);
    }

    private Nodo<T> agregarEnSubArbol(Nodo<T> raiz, T valor) {
        if (raiz == null) {
            return new Nodo<>(valor);
        }

        if (valor.compareTo(raiz.valor) < 0) {
            raiz.izquierda = agregarEnSubArbol(raiz.izquierda, valor);
        } else if (valor.compareTo(raiz.valor) > 0) {
            raiz.derecha = agregarEnSubArbol(raiz.derecha, valor);
        }

        return raiz;
    }

    /**
     * Elimina un valor del árbol.
     *
     * @param valor El valor a eliminar.
     */
    public void eliminar(T valor) {
        raiz = eliminarEnSubArbol(raiz, valor);
    }

    private Nodo<T> eliminarEnSubArbol(Nodo<T> raiz, T valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor.compareTo(raiz.valor) < 0) {
            raiz.izquierda = eliminarEnSubArbol(raiz.izquierda, valor);
        } else if (valor.compareTo(raiz.valor) > 0) {
            raiz.derecha = eliminarEnSubArbol(raiz.derecha, valor);
        } else {
            if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if (raiz.derecha == null) {
                return raiz.izquierda;
            }

            raiz.valor = encontrarMinimo(raiz.derecha);
            raiz.derecha = eliminarEnSubArbol(raiz.derecha, raiz.valor);
        }

        return raiz;
    }

    /**
     * Realiza un recorrido en orden del árbol e imprime los valores.
     */
    public void recorridoEnOrden() {
        recorridoEnOrdenRec(raiz);
        System.out.println();
    }

    private void recorridoEnOrdenRec(Nodo<T> raiz) {
        if (raiz != null) {
            recorridoEnOrdenRec(raiz.izquierda);
            System.out.print(raiz.valor + " ");
            recorridoEnOrdenRec(raiz.derecha);
        }
    }

    /**
     * Busca un valor en el árbol.
     *
     * @param valor El valor a buscar.
     * @return Verdadero si se encuentra el valor; de lo contrario, falso.
     */
    public boolean buscar(T valor) {
        return buscarEnSubArbol(raiz, valor);
    }

    private boolean buscarEnSubArbol(Nodo<T> raiz, T valor) {
        if (raiz == null) {
            return false;
        }

        if (raiz.valor.equals(valor)) {
            return true;
        }

        if (valor.compareTo(raiz.valor) < 0) {
            return buscarEnSubArbol(raiz.izquierda, valor);
        } else {
            return buscarEnSubArbol(raiz.derecha, valor);
        }
    }

    private T encontrarMinimo(Nodo<T> raiz) {
        T minimo = raiz.valor;
        while (raiz.izquierda != null) {
            minimo = raiz.izquierda.valor;
            raiz = raiz.izquierda;
        }
        return minimo;
    }
}
