package arbolRojoNegro;

import utils.arbolRojoNegro.ArbolRojoNegro;

public class Main {
    public static void main(String[] args) {
        // Crear un árbol rojo-negro de enteros
        ArbolRojoNegro<Integer> arbolRN = new ArbolRojoNegro<>();

        // Insertar valores al árbol
        arbolRN.insertar(10);
        arbolRN.insertar(20);
        arbolRN.insertar(5);
        arbolRN.insertar(6);
        arbolRN.insertar(12);

        // Realizar un recorrido en orden e imprimir los valores
        System.out.println("Recorrido en orden del árbol rojo-negro:");
        arbolRN.recorridoEnOrden();
    }
}
