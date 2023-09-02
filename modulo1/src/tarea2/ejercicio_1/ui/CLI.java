/**
 * @author Eduardo Orozco Osuna
 */

package tarea2.ejercicio_1.ui;

import tarea2.ejercicio_1.process.Pasajero;
import tarea2.utils.ListaEnlazada;
import tarea3.ui.AccionMenu;
import tarea3.ui.Menu;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CLI {

    private static ListaEnlazada<Pasajero> pasajeros = new ListaEnlazada<>();
    private static Menu menu = new Menu();

    private static String ADD_PASSENGER = "Agregar pasajero (al final de la lista).";
    private static String REMOVE_PASSANGER_AT = "Eliminar pasajero (especificado por el usuario).";
    private static String GET_PASSENGER_DATA = "Visualizar los datos de un pasajero determinado.";

    /**
     * Other usefull texts.
     */
    private static String INPUT_PASSENGER_DATA = "Ingrese la información del pasajero a agregar;";
    private static String INPUT_PASSENGER_TO_REMOVE_DATA = "Ingrese la información del pasajero a eliminar;";
    private static String INPUT_PASSENGER_NAME = "Ingrese el nombre del pasajero:";
    private static String IF_TICKET_ALREADY_BOUGHT = "¿El pasajero ya compró su ticket?\na) Sí.\nb) No.";
    private static String INPUT_TICKET_NUMBER = "Ingrese el número del ticket del pasajero:";
    private static String INPUT_PASSENGER_INDEX = "Ingrese el índice del pasajero:";
    private static String NUMERIC_TYPE_ERROR = "El valor ingresado no posee un formato numérico. Intente de nuevo: ";

    /**
     * Menu2 actions.
     */
    /**
     * Defines the action that adds a passenger at the end of the list.
     */
    private static AccionMenu agregarPasajero = () -> {
        String nombrePasajero = new String();
        char comproTicket = ' ';
        int numeroTicket;
        Scanner entrada = new Scanner(System.in);

        System.out.println(INPUT_PASSENGER_DATA);

        System.out.println(INPUT_PASSENGER_NAME);
        nombrePasajero = entrada.next();

        while(comproTicket != 'a' && comproTicket != 'b'){
            System.out.println(IF_TICKET_ALREADY_BOUGHT);
            comproTicket = entrada.next().charAt(0);
        }
        if(comproTicket == 'a'){
            System.out.println(INPUT_TICKET_NUMBER);
            while (true) {
                try {
                    numeroTicket = entrada.nextInt();
                    entrada.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    entrada.nextLine();
                    System.out.print(NUMERIC_TYPE_ERROR);
                }
            }
            pasajeros.add(new Pasajero(nombrePasajero, numeroTicket));
        }
        else if(comproTicket == 'b'){
            pasajeros.add(new Pasajero(nombrePasajero));
        }
    };

    private static AccionMenu removerPasajeroPorBoleto = () -> {
        try {
            String nombrePasajero = new String();
            char comproTicket = ' ';
            int numeroTicket;
            Scanner entrada = new Scanner(System.in);
            Pasajero p = null;

            System.out.println(INPUT_PASSENGER_TO_REMOVE_DATA);

            System.out.println(INPUT_PASSENGER_NAME);
            nombrePasajero = entrada.next();

            while (comproTicket != 'a' && comproTicket != 'b') {
                System.out.println(IF_TICKET_ALREADY_BOUGHT);
                comproTicket = entrada.next().charAt(0);
            }
            if (comproTicket == 'a') {
                System.out.println(INPUT_TICKET_NUMBER);
                while (true) {
                    try {
                        numeroTicket = entrada.nextInt();
                        entrada.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        entrada.nextLine();
                        System.out.print(NUMERIC_TYPE_ERROR);
                    }
                }
                p = new Pasajero(nombrePasajero, numeroTicket);
            } else if (comproTicket == 'b') {
                p = new Pasajero(nombrePasajero);
            }

            pasajeros.remove(p);
        }
        catch (NoSuchElementException e){
            System.out.println("Ocurrió el siguiente problema al ejecutar la operación: " + e);
        }
    };

    private static AccionMenu obtenerPasajeroEn = () -> {
        try {
            int indice;
            Scanner entrada = new Scanner(System.in);

            System.out.println(INPUT_PASSENGER_INDEX);
            while (true) {
                try {
                    indice = entrada.nextInt();
                    entrada.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    entrada.nextLine();
                    System.out.print(NUMERIC_TYPE_ERROR);
                }
            }

            System.out.println(pasajeros.get(indice));
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Ocurrió el siguiente problema al ejecutar la operación: " + e);
        }
    };

    static {
        menu
                .agregarOpcion(ADD_PASSENGER, agregarPasajero)
                .agregarOpcion(REMOVE_PASSANGER_AT, removerPasajeroPorBoleto)
                .agregarOpcion(GET_PASSENGER_DATA, obtenerPasajeroEn)
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
