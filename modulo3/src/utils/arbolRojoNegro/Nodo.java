package utils.arbolRojoNegro;

import java.awt.*;

class Nodo<T extends Comparable<T>> {
    T valor;
    Nodo<T> izquierda, derecha, padre;
    Color color;

    public Nodo(T valor) {
        this.valor = valor;
        this.color = Color.RED;  // Por defecto, un nuevo nodo se coloca como rojo
    }
}
