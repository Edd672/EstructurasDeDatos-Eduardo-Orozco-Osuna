/**
 * @author Eduardo Orozco Osuna
 */

package tarea3.ui;

import tarea3.process.Expresiones;
import tarea3.utils.Pila;

import java.util.Scanner;

public class CLI {
    private static Menu menu = new Menu();

    private static String CONVERTIR_INF_PSTFX = "Convertir expresión de notación infija a notación postfija.";

    private static String ENTRADA_EXP_INF = "Ingresa una expresión matemática en notación infija:";
    private static String SALIDA_INF = "La expresión matemática en notación infija que ingresaste es: %s\n";
    private static String SALIDA_PSTFX = "La expresión equivalente en notación postfija es: %s\n";
    private static String RESPUESTA = "El resultado de evaluar la expresión dada es: %f\n";

    private static AccionMenu infijaAPostfija = () -> {
        String expInfija;
        Pila<String> pilaExp;
        double respuesta;
        Scanner entrada = new Scanner(System.in);

        System.out.println(ENTRADA_EXP_INF);
        expInfija = entrada.nextLine();
        System.out.printf(SALIDA_INF, expInfija);
        pilaExp = Expresiones.cadenaAStack(expInfija);

        expInfija = Expresiones.cadenaInfijaAPostfija(expInfija);
        pilaExp = Expresiones.stackInfijaAPostfija(pilaExp);
        System.out.printf(SALIDA_PSTFX, expInfija);

        respuesta = Expresiones.calcularExpresionPostfija(pilaExp);
        System.out.printf(RESPUESTA, respuesta);
    };

    static {
        menu
                .agregarOpcion(CONVERTIR_INF_PSTFX, infijaAPostfija)
                .agregarOpcionSalir();
    }

    public static void launchApp() {
        do {
            menu.mostrarMenu();
            menu.solicitarOpcion();
            int opcion = menu.leerOpcion();
            menu.ejecutarOpcion(opcion);
        } while (menu.estaActivo());
    }
}
