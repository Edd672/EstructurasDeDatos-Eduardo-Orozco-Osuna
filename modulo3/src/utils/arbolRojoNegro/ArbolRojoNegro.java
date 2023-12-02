package utils.arbolRojoNegro;


import java.awt.*;

public class ArbolRojoNegro<T extends Comparable<T>> {
    private Nodo<T> raiz;
    private Nodo<T> NIL;  // Nodo nulo utilizado para representar las hojas

    public ArbolRojoNegro() {
        NIL = new Nodo<>(null);
        NIL.color = Color.BLACK;  // El nodo nulo es negro
        raiz = NIL;
    }

    // Métodos para la inserción y equilibrio del árbol
    public void insertar(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        insertarNodo(nuevoNodo);
        arreglarInsercion(nuevoNodo);
    }

    private void insertarNodo(Nodo<T> nodo) {
        Nodo<T> y = NIL;
        Nodo<T> x = raiz;

        while (x != NIL) {
            y = x;
            if (nodo.valor.compareTo(x.valor) < 0) {
                x = x.izquierda;
            } else {
                x = x.derecha;
            }
        }

        nodo.padre = y;
        if (y == NIL) {
            raiz = nodo;
        } else if (nodo.valor.compareTo(y.valor) < 0) {
            y.izquierda = nodo;
        } else {
            y.derecha = nodo;
        }

        nodo.izquierda = NIL;
        nodo.derecha = NIL;
        nodo.color = Color.RED;
    }

    private void arreglarInsercion(Nodo<T> nodo) {
        while (nodo.padre.color == Color.RED) {
            if (nodo.padre == nodo.padre.padre.izquierda) {
                Nodo<T> tio = nodo.padre.padre.derecha;

                if (tio.color == Color.RED) {
                    nodo.padre.color = Color.BLACK;
                    tio.color = Color.BLACK;
                    nodo.padre.padre.color = Color.RED;
                    nodo = nodo.padre.padre;
                } else {
                    if (nodo == nodo.padre.derecha) {
                        nodo = nodo.padre;
                        rotarIzquierda(nodo);
                    }

                    nodo.padre.color = Color.BLACK;
                    nodo.padre.padre.color = Color.RED;
                    rotarDerecha(nodo.padre.padre);
                }
            } else {
                Nodo<T> tio = nodo.padre.padre.izquierda;

                if (tio.color == Color.RED) {
                    nodo.padre.color = Color.BLACK;
                    tio.color = Color.BLACK;
                    nodo.padre.padre.color = Color.RED;
                    nodo = nodo.padre.padre;
                } else {
                    if (nodo == nodo.padre.izquierda) {
                        nodo = nodo.padre;
                        rotarDerecha(nodo);
                    }

                    nodo.padre.color = Color.BLACK;
                    nodo.padre.padre.color = Color.RED;
                    rotarIzquierda(nodo.padre.padre);
                }
            }
        }

        raiz.color = Color.BLACK;
    }

    private void rotarIzquierda(Nodo<T> x) {
        Nodo<T> y = x.derecha;
        x.derecha = y.izquierda;

        if (y.izquierda != NIL) {
            y.izquierda.padre = x;
        }

        y.padre = x.padre;

        if (x.padre == NIL) {
            raiz = y;
        } else if (x == x.padre.izquierda) {
            x.padre.izquierda = y;
        } else {
            x.padre.derecha = y;
        }

        y.izquierda = x;
        x.padre = y;
    }

    private void rotarDerecha(Nodo<T> y) {
        Nodo<T> x = y.izquierda;
        y.izquierda = x.derecha;

        if (x.derecha != NIL) {
            x.derecha.padre = y;
        }

        x.padre = y.padre;

        if (y.padre == NIL) {
            raiz = x;
        } else if (y == y.padre.derecha) {
            y.padre.derecha = x;
        } else {
            y.padre.izquierda = x;
        }

        x.derecha = y;
        y.padre = x;
    }

    // Método para realizar un recorrido en orden
    public void recorridoEnOrden() {
        recorridoEnOrden(raiz);
        System.out.println();
    }

    private void recorridoEnOrden(Nodo<T> nodo) {
        if (nodo != NIL) {
            recorridoEnOrden(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            recorridoEnOrden(nodo.derecha);
        }
    }
}
