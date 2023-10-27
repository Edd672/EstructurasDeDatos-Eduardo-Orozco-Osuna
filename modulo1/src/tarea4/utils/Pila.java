
/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea4.utils;

import java.util.Optional;

/**
 * Una estructura de datos genérica de pila implementada usando una lista enlazada.
 * @param <E> el tipo de elementos contenidos en esta pila.
 */
public class Pila<E> {
    private ListaEnlazada<E> pila;

    /**
     * Construye una pila vacía.
     */
    public Pila() {
        pila = new ListaEnlazada<>();
    }

    /**
     * Empuja un elemento en la parte superior de esta pila.
     * @param elementoParaEmpujar el elemento a empujar en esta pila
     */
    public void empujar(E elementoParaEmpujar) {
        pila.agregarPrimero(elementoParaEmpujar);
    }

    /**
     * Obtiene, pero no elimina, el elemento en la parte superior de esta pila.
     * @return un Optional que contiene el elemento en la parte superior de la pila, o un Optional vacío si la pila está vacía
     */
    public Optional<E> vistazo() {
        return pila.obtener(0);
    }

    /**
     * Elimina y devuelve el elemento en la parte superior de esta pila.
     * @return un Optional que contiene el elemento eliminado de la parte superior de la pila, o un Optional vacío si la pila está vacía
     */
    public Optional<E> pop() {
        Optional<E> eliminado = pila.obtener(0);
        pila.eliminar(eliminado.get());
        return eliminado;
    }

    /**
     * Obtiene el número de elementos en esta pila.
     * @return el número de elementos en esta pila
     */
    public int obtenerTamaño() {
        return pila.obtenerTamaño();
    }

    /**
     * Comprueba si esta pila está vacía.
     * @return true si la pila está vacía, de lo contrario, false
     */
    public boolean estáVacía() {
        return !(pila.obtenerTamaño() > 0);
    }

    /**
     * Devuelve una nueva pila con elementos en orden inverso en comparación con esta pila.
     * @return una nueva pila con elementos en orden inverso
     */
    public Pila<E> inversa() {
        Pila<E> invertida = new Pila<>();

        for (int i = 0; i < pila.obtenerTamaño(); ++i) {
            invertida.empujar(pila.obtener(i).get());
        }

        return invertida;
    }

    /**
     * Devuelve una nueva pila con elementos alternados entre esta pila y otra pila.
     * @param pilaB la otra pila para alternar elementos
     * @return una nueva pila con elementos alternados
     */
    public Pila<E> alternar(Pila<E> pilaB) {
        Pila<E> alternada = new Pila<>();

        for (int i = 0; i < Math.max(pila.obtenerTamaño(), pilaB.pila.obtenerTamaño()); ++i) {
            try {
                alternada.empujar(pilaB.pila.obtener(pila.obtenerTamaño() - i - 1).get());
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                alternada.empujar(pila.obtener(pila.obtenerTamaño() - i - 1).get());
            } catch (IndexOutOfBoundsException e) {
            }
        }

        return alternada;
    }
}
