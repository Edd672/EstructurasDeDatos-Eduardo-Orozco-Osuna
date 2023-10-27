package tarea4.ejercicio_1.ui;

import tarea4.utils.Cola;

import java.util.Scanner;

/**
 * Eduardo Oroxco Osuna
 */
public class CLI {
    /**
     * Datos utilizados por el paquete de procesamiento.
     */
    private static Cola<Integer> cola = new Cola<>();
    private static Scanner entrada = new Scanner(System.in);

    /**
     * Creación del menú de usuario.
     */
    private static Menu menu = new Menu();

    /**
     * Opciones del menú.
     */
    private static String AGREGAR = "Agregar un nuevo elemento al final de la cola";
    private static String ELIMINAR = "Eliminar el primer elemento de la cola";
    private static String CONSULTAR = "Consultar el primer elemento de la cola";
    private static String TAMAÑO = "Conocer el número de elementos almacenados en la cola";
    private static String ESTA_VACÍA = "Verificar si la cola está vacía";

    /**
     * Otros textos útiles.
     */
    private static String AGREGAR_ELEMENTO = "Ingrese el valor del elemento a agregar al final de la cola:";
    private static String CONSULTAR_ELEMENTO = "El valor del elemento almacenado al principio de la cola es: %s\n";
    private static String TAMAÑO_COLA = "El número de elementos almacenados en la cola es: %d\n";
    private static String COLA_VACÍA = "La cola está vacía.\n";
    private static String COLA_NO_VACÍA = "La cola no está vacía.\n";

    /**
     * Acciones del menú.
     */
    private static MenuActionPrototype agregarElemento = () -> {
        int nuevoElemento;

        System.out.println(AGREGAR_ELEMENTO);
        nuevoElemento = entrada.nextInt();

        cola.encolar(nuevoElemento);
    };

    private static MenuActionPrototype eliminarElemento = () -> {
        cola.desencolar();
    };

    private static MenuActionPrototype consultarElemento = () -> {
        System.out.printf(CONSULTAR_ELEMENTO, cola.frente().get());
    };

    private static MenuActionPrototype tamañoCola = () -> {
        System.out.printf(TAMAÑO_COLA, cola.tamano());
    };

    private static MenuActionPrototype colaVacia = () -> {
        if (cola.estaVacia()) {
            System.out.printf(COLA_VACÍA);
        } else {
            System.out.printf(COLA_NO_VACÍA);
        }
    };

    /**
     * Agregar las opciones del menú y las acciones.
     */
    static {
        menu
                .agregarOpción(AGREGAR, agregarElemento)
                .agregarOpción(ELIMINAR, eliminarElemento)
                .agregarOpción(CONSULTAR, consultarElemento)
                .agregarOpción(TAMAÑO, tamañoCola)
                .agregarOpción(ESTA_VACÍA, colaVacia)
                .agregarOpciónSalida();
    }

    /**
     * Mostrar el menú y ejecutar las acciones asociadas a cada opción.
     */
    public static void iniciarAplicación() {
        do {
            menu.mostrarMenú();
            menu.solicitarOpción();
            int opción = menu.leerOpción();
            menu.ejecutarOpción(opción);
        } while (menu.estáActivo());
    }
}
