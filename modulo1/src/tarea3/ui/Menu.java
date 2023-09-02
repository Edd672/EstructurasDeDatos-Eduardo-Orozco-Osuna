/**
 * @author Eduardo Orozco Osuna
 */

package tarea3.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ClaseMenu contiene variables y funciones que definen una plantilla de un menú para el usuario.
 */
public class Menu {
    /**
     * Textos que pueden aparecer al interactuar con el menú.
     */
    private String BIENVENIDA_MENU = "Menú Principal";
    private String SOLICITUD_OPCION = "Por favor, ingresa el número de la opción que deseas:\n";
    private String ERROR_TIPO_NUMERICO = "El valor ingresado no es numérico. Intenta nuevamente: ";
    private String ERROR_FUERA_RANGO = "Opción no válida. Por favor, intenta de nuevo: ";
    private String FIN_DEL_PROGRAMA = "Programa finalizado.";

    /**
     * listaOpciones almacena las cadenas de texto correspondientes a las opciones mostradas al usuario + una opción de salida.
     * listaAccionesMenu almacena las funciones asociadas a cada opción mostrada al usuario, excepto la opción de salida.
     */
    private ArrayList<String> listaOpciones = new ArrayList<>();
    private ArrayList<AccionMenu> listaAccionesMenu = new ArrayList<>();

    /**
     * activo establece el estado de disponibilidad del menú al usuario y ayuda a determinar si el menú debe mantenerse visible o no.
     */
    private boolean activo = true;

    /**
     * finalizarMenu indica que el menú debe dejar de mostrarse al usuario.
     */
    public void finalizarMenu() {
        activo = false;
    }

    /**
     * estaActivo determina el estado de disponibilidad del menú al usuario.
     */
    public boolean estaActivo() {
        return activo;
    }

    /**
     * agregarOpcion agrega una opción al menú junto con su función asociada.
     * @param opcion: un texto que contiene una de las opciones del menú.
     * @param accionMenu: la función que se debe ejecutar al seleccionar la opción asociada.
     * @return this: el menú actual con las modificaciones correspondientes.
     */
    public Menu agregarOpcion(String opcion, AccionMenu accionMenu) {
        listaOpciones.add(opcion);
        listaAccionesMenu.add(accionMenu);
        return this;
    }

    /**
     * agregarOpcionSalir agrega una opción de salida al final del menú.
     */
    public void agregarOpcionSalir() {
        listaOpciones.add("Salir");
    }

    /**
     * limpiarMenu elimina todas las opciones y acciones asociadas del menú.
     */
    public void limpiarMenu() {
        listaOpciones.clear();
        listaAccionesMenu.clear();
    }

    /**
     * mostrarMenu muestra las opciones dentro del menú.
     */
    public void mostrarMenu() {
        System.out.println(BIENVENIDA_MENU);
        for (int i = 0; i < listaOpciones.size(); i++) {
            System.out.println(i + 1 + ") " + listaOpciones.get(i));
        }
    }

    public void solicitarOpcion(){
        System.out.print(SOLICITUD_OPCION);
    }

    /**
     * leerOpcion lee la entrada del usuario y valida que se haya proporcionado en un formato adecuado.
     * @return opcion: un número que representa el índice de una opción específica.
     */
    public int leerOpcion() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            try {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                if (opcion < 0 || opcion > listaOpciones.size()) {
                    System.out.print(ERROR_FUERA_RANGO);
                    continue;
                }
                else if(opcion == 0){
                    finalizarMenu();
                    opcion = listaOpciones.size();
                }
                return opcion;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.print(ERROR_TIPO_NUMERICO);
            }
        }
    }

    /**
     * seleccionarYRealizarOpcion ejecuta la función asociada a la opción seleccionada por el usuario.
     * @param opcion: el número que representa el índice de la opción seleccionada por el usuario.
     */
    public void ejecutarOpcion(int opcion) {
        if (opcion == listaOpciones.size()) {
            finalizarMenu();
            System.out.println(FIN_DEL_PROGRAMA);
            return;
        }
        listaAccionesMenu.get(opcion - 1).accionDefinida();
    }
}
