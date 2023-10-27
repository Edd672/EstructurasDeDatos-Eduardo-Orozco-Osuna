/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea3.ui;

import tarea3.process.Notaciones;
import tarea3.utils.Pila;

import java.util.Scanner;

/**
 * La clase CLI contiene todos los datos que serán procesados por la clase Notaciones.
 * Define los detalles del menú de usuario y lo ejecuta.
 */
public class CLI {
    /**
     * Datos utilizados por el paquete proceso.
     */

    /**
     * Creación del menú de usuario.
     */
    private static Menu menu = new Menu();

    /**
     * Opciones del menú.
     */
    private static String INFIX_TO_POSTFIX = "Convertir una expresión de notación infija a su equivalente en notación posfija.";

    /**
     * Otros textos útiles.
     */
    private static String INPUT_INFIX_EXPRESSION = "Ingrese una expresión matemática en notación infija:";
    private static String OUTPUT_INFIX = "La expresión matemática en notación infija ingresada es: %s\n";
    private static String OUTPUT_POSTFIX = "La expresión equivalente en notación posfija es: %s\n";
    private static String ANS = "El resultado de evaluar la expresión dada es: %f\n";

    /**
     * Acciones del menú.
     */
    /**
     * Define la acción que convierte una expresión infija a posfija.
     */
    private static MenuActionPrototype infijoAPosfijo = () -> {
        String expresion;
        Pila<String> pilaExpresion;
        double resultado;
        Scanner entrada = new Scanner(System.in);

        System.out.println(INPUT_INFIX_EXPRESSION);
        expresion = entrada.next();
        System.out.printf(OUTPUT_INFIX, expresion);
        pilaExpresion = Notaciones.cadenaAPila(expresion);

        expresion = Notaciones.cadenaInfijaAPostfija(expresion);
        pilaExpresion = Notaciones.pilaInfijaAPostfija(pilaExpresion);
        System.out.printf(OUTPUT_POSTFIX, expresion);

        resultado = Notaciones.evaluarExpresiónPostfija(pilaExpresion);
        System.out.printf(ANS, resultado);
    };

    /**
     * Agregar las opciones del menú y sus acciones.
     */
    static {
        menu
                .agregarOpcion(INFIX_TO_POSTFIX, infijoAPosfijo)
                .agregarOpcionSalir();
    }

    /**
     * Muestra el menú y ejecuta las acciones asociadas a cada opción.
     */
    public static void iniciarApp() {
        do {
            menu.mostrarMenu();
            menu.solicitarOpcion();
            int opción = menu.leerOpcion();
            menu.ejecutarOpcion(opción);
        } while (menu.isAlive());
    }
}
