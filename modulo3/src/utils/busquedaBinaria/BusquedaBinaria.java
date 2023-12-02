package utils.busquedaBinaria;

public class BusquedaBinaria {
    // Método de búsqueda binaria con interpolación
    public static <T extends Comparable<T>> int buscarConInterpolacion(T[] array, T elemento) {
        int izquierda = 0;
        int derecha = array.length - 1;

        while (izquierda <= derecha && elemento.compareTo(array[izquierda]) >= 0 && elemento.compareTo(array[derecha]) <= 0) {
            // Fórmula de interpolación para estimar la posición del elemento
            int posicionInterpolada = izquierda + (((derecha - izquierda) / (array[derecha].compareTo(array[izquierda])))
                    * (elemento.compareTo(array[izquierda])));

            if (array[posicionInterpolada].equals(elemento)) {
                return posicionInterpolada; // Elemento encontrado
            }

            if (array[posicionInterpolada].compareTo(elemento) < 0) {
                izquierda = posicionInterpolada + 1;
            } else {
                derecha = posicionInterpolada - 1;
            }
        }

        return -1; // Elemento no encontrado
    }
}
