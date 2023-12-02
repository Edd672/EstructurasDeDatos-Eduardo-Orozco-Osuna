/**
 * @author Eduardo Orozco Osuna
 */

package tarea14;

import utils.grafo.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        // Crear un árbol binario de enteros
        ArbolBinario<Integer> arbolEnteros = new ArbolBinario<>();

        // Agregar valores al árbol
        arbolEnteros.agregar(50);
        arbolEnteros.agregar(30);
        arbolEnteros.agregar(70);
        arbolEnteros.agregar(20);
        arbolEnteros.agregar(40);
        arbolEnteros.agregar(60);
        arbolEnteros.agregar(80);

        // Realizar un recorrido en orden e imprimir los valores
        System.out.println("Recorrido en orden del árbol:");
        arbolEnteros.recorridoEnOrden();

        // Buscar un valor en el árbol
        int valorBuscado = 40;
        if (arbolEnteros.buscar(valorBuscado)) {
            System.out.println("El valor " + valorBuscado + " está presente en el árbol.");
        } else {
            System.out.println("El valor " + valorBuscado + " no está presente en el árbol.");
        }

        // Eliminar un valor del árbol
        int valorEliminar = 30;
        arbolEnteros.eliminar(valorEliminar);

        // Realizar nuevamente un recorrido en orden e imprimir los valores después de la eliminación
        System.out.println("Recorrido en orden del árbol después de eliminar " + valorEliminar + ":");
        arbolEnteros.recorridoEnOrden();
    }
}

