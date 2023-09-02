/**
 * @author Eduardo Orozco Osuna
 */

package tarea2.ejercicio_2.ui;

import tarea2.ejercicio_2.process.JugadorFutbol;
import tarea2.utils.ListaEnlazada;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

/**
 * InterfazUsuario contiene toda la información que será procesada por la clase EquipoFutbol.
 * InterfazUsuario define los detalles del menú de usuario y lo ejecuta.
 */
public class CLI {
    /**
     * Datos utilizados por el paquete proceso.
     */
    private static ListaEnlazada<JugadorFutbol> capitanesFutbol = new ListaEnlazada<>();
    private static ListaEnlazada<JugadorFutbol> jugadoresFutbol = new ListaEnlazada<>();
    private static LinkedList<ListaEnlazada<JugadorFutbol>> equiposFutbol = new LinkedList<>();

    /**
     * Creación del menú de usuario.
     */
    private static Menu menuUsuario = new Menu();

    /**
     *  Opciones del Menú2.
     */
    private static String AGREGAR_CAPITANES = "Agregar capitanes.";
    private static String AGREGAR_JUGADORES = "Agregar jugadores.";
    private static String CREAR_EQUIPOS = "Crear equipos.";
    private static String MOSTRAR_EQUIPOS = "Mostrar equipos.";

    /**
     * Otros textos útiles.
     */
    private static String INFO_CAPITAN = "Ingrese la información de los capitanes:";
    private static String AGREGAR_CAPITAN = "¿Qué desea hacer?\na) Agregar un nuevo capitán.\nb) Finalizar agregado de capitanes:";
    private static String INGRESAR_DATOS_CAPITAN = "Ingrese la información del capitán a agregar:";
    private static String INGRESAR_NOMBRE_CAPITAN = "Ingrese el nombre del capitán:";
    private static String INGRESAR_NUMERO_CAMISA_CAPITAN = "Ingrese el número de la camisa del capitán:";
    private static String INFO_JUGADOR = "Ingrese la información de los jugadores:";
    private static String AGREGAR_JUGADOR = "¿Qué desea hacer?\na) Agregar un nuevo jugador.\nb) Finalizar agregado de jugadores:";
    private static String INGRESAR_DATOS_JUGADOR = "Ingrese la información del jugador a agregar:";
    private static String INGRESAR_NOMBRE_JUGADOR = "Ingrese el nombre del jugador:";
    private static String INGRESAR_NUMERO_CAMISA_JUGADOR = "Ingrese el número de la camisa del jugador:";
    private static String INGRESAR_EQUIPO = "Crear el equipo %d:\n";
    private static String INGRESAR_CAPITAN_EQUIPO = "Indique el índice del capitán del equipo %d:\n";
    private static String INGRESAR_JUGADOR_EQUIPO = "Indique el índice del jugador del equipo %d:\n";
    private static String ERROR_JUGADOR_FUTBOL = "El jugador ingresado ya está en otro equipo. Inténtelo nuevamente.";
    private static String ERROR_TIPO_NUMERICO = "El valor ingresado no tiene un formato numérico. Inténtelo nuevamente: ";

    /**
     * Acciones del Menú2.
     */
    /**
     * Define la acción que agrega los capitanes a una lista.
     */
    private static AccionMenu agregar_capitanes = () -> {
        while(true) {
            char opcion = ' ';
            String nombreCapitan = " ";
            int numeroCamisaCapitan = -1;
            Scanner entrada = new Scanner(System.in);

            System.out.println(INFO_CAPITAN);
            while (opcion != 'a' && opcion != 'b') {
                System.out.println(AGREGAR_CAPITAN);
                opcion = entrada.next().charAt(0);
            }

            if (opcion == 'a') {
                System.out.println(INGRESAR_DATOS_CAPITAN);
                System.out.println(INGRESAR_NOMBRE_CAPITAN);
                nombreCapitan = entrada.next();

                System.out.println(INGRESAR_NUMERO_CAMISA_CAPITAN);
                while (true) {
                    try {
                        numeroCamisaCapitan = entrada.nextInt();
                        entrada.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        entrada.nextLine();
                        System.out.print(ERROR_TIPO_NUMERICO);
                    }
                }

                capitanesFutbol.add(new JugadorFutbol(nombreCapitan, numeroCamisaCapitan, true));
            } else {
                break;
            }
        }
    };

    /**
     * Define la acción que agrega los jugadores a una lista.
     */
    private static AccionMenu agregar_jugadores = () -> {
        while(true) {
            char opcion = ' ';
            String nombreJugador = " ";
            int numeroCamisaJugador = -1;
            Scanner entrada = new Scanner(System.in);

            System.out.println(INFO_JUGADOR);
            while (opcion != 'a' && opcion != 'b') {
                System.out.println(AGREGAR_JUGADOR);
                opcion = entrada.next().charAt(0);
            }

            if (opcion == 'a') {
                System.out.println(INGRESAR_DATOS_JUGADOR);
                System.out.println(INGRESAR_NOMBRE_JUGADOR);
                nombreJugador = entrada.next();
                System.out.println(INGRESAR_NUMERO_CAMISA_JUGADOR);
                numeroCamisaJugador = entrada.nextInt();

                jugadoresFutbol.add(new JugadorFutbol(nombreJugador, numeroCamisaJugador, false));
            } else {
                break;
            }
        }
    };

    /**
     * Define la acción que crea los equipos.
     */
    private static AccionMenu crear_equipos = () -> {
        int indice;
        char opcion = ' ';
        Scanner entrada = new Scanner(System.in);

        for(int i = 0; i < capitanesFutbol.size(); ++i){
            System.out.printf(INGRESAR_EQUIPO, i);
            equiposFutbol.add(new ListaEnlazada<>());

            // Agregando capitán.
            while(true) {
                try {
                    System.out.printf(INGRESAR_CAPITAN_EQUIPO, i);
                    while (true) {
                        try {
                            indice = entrada.nextInt();
                            entrada.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            entrada.nextLine();
                            System.out.print(ERROR_TIPO_NUMERICO);
                        }
                    }

                    JugadorFutbol capitanFutbol = capitanesFutbol.get(indice).get();
                    if (!capitanFutbol.getHasTeam()) {
                        capitanFutbol.setHasTeam(true);

                        equiposFutbol.get(i).add(capitanFutbol);
                        break;
                    } else {
                        System.out.println(ERROR_JUGADOR_FUTBOL);
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ha ocurrido el siguiente problema al ejecutar la operación: " + e);
                }
            }

            // Agregando jugadores.
            while(true){
                while (opcion != 'a' && opcion != 'b') {
                    System.out.println(AGREGAR_JUGADOR);
                    opcion = entrada.next().charAt(0);
                }
                if (opcion == 'a') {
                    try {
                        System.out.printf(INGRESAR_JUGADOR_EQUIPO, i);
                        while (true) {
                            try {
                                indice = entrada.nextInt();
                                entrada.nextLine();
                                break;
                            } catch (InputMismatchException e) {
                                entrada.nextLine();
                                System.out.print(ERROR_TIPO_NUMERICO);
                            }
                        }

                        JugadorFutbol jugadorFutbol = jugadoresFutbol.get(indice).get();
                        if (!jugadorFutbol.getHasTeam()) {
                            jugadorFutbol.setHasTeam(true);
                            equiposFutbol.get(i).add(jugadorFutbol);
                            opcion = ' ';
                        } else {
                            System.out.println(ERROR_JUGADOR_FUTBOL);
                        }
                    }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("Ha ocurrido el siguiente problema al ejecutar la operación: " + e);
                    }
                } else {
                    break;
                }
            }
        }
    };

    private static AccionMenu mostrarEquipos = () -> {
        for (int i = 0; i < equiposFutbol.size(); ++i) {
            System.out.println("Equipo " + i);
            for (int j = 0; j < equiposFutbol.get(i).size(); j++) {
                Optional<JugadorFutbol> jugadorFutbol = equiposFutbol.get(i).get(j);
                System.out.println(jugadorFutbol);
            }
        }
    };

    /**
     * Agregando las opciones y acciones al menú.
     */
    static {
        menuUsuario
                .agregarOpcion(AGREGAR_CAPITANES, agregar_capitanes)
                .agregarOpcion(AGREGAR_JUGADORES, agregar_jugadores)
                .agregarOpcion(CREAR_EQUIPOS, crear_equipos)
                .agregarOpcion(MOSTRAR_EQUIPOS, mostrarEquipos)
                .agregarOpcionSalir();
    }

    /**
     * Mostrando el menú y ejecutando las acciones asociadas a cada opción.
     */
    public static void launchApp() {
        do {
            menuUsuario.mostrarMenu();
            menuUsuario.solicitarOpcion();
            int opcion = menuUsuario.leerOpcion();
            menuUsuario.ejecutarOpcion(opcion);
        } while (menuUsuario.estaActivo());
    }
}
