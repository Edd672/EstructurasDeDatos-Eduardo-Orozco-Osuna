/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea3.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La clase Menu proporciona un conjunto de variables y miembros que describen una plantilla de menú de usuario.
 */
public class Menu {
    /**
     * Textos que pueden aparecer al interactuar con el menú.
     */
    private String BIENVENIDA_MENU = "Menú";
    private String SOLICITAR_OPCION = "Ingrese el número correspondiente a la opción que desea elegir:\n";
    private String ERROR_TIPO_NUMERICO = "El valor ingresado no tiene un formato numérico. Intente nuevamente: ";
    private String OPCION_NO_DISPONIBLE = "Opción no válida. Intente nuevamente: ";
    private String FIN_DEL_PROGRAMA = "Programa finalizado.";

    /**
     * optionList almacena las cadenas que corresponden a las opciones mostradas al usuario, además de una opción para salir.
     * menuActionPrototypeList almacena las funciones asociadas a cada opción mostrada al usuario, excepto la opción de salir.
     */
    private ArrayList<String> optionList = new ArrayList<>();
    private ArrayList<MenuActionPrototype> menuActionPrototypeList = new ArrayList<>();

    /**
     * alive establece el estado de disponibilidad del menú para el usuario y ayuda a determinar si el menú debe seguir mostrándose o no.
     */
    private boolean alive = true;

    /**
     * killMenu indica que el menú de usuario debe dejar de mostrarse.
     */
    public void killMenu() {
        alive = false;
    }

    /**
     * isAlive determina el estado de disponibilidad del menú para el usuario.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * addOption agrega una opción al menú de usuario junto con la acción asociada.
     * @param opcion: el texto que representa una de las opciones del menú.
     * @param menuActionPrototype: el método que se ejecutará al seleccionar la opción.
     * @return this: el menú actual con las modificaciones correspondientes.
     */
    public Menu agregarOpcion(String opcion, MenuActionPrototype menuActionPrototype) {
        optionList.add(opcion);
        menuActionPrototypeList.add(menuActionPrototype);
        return this;
    }

    /**
     * addExitOption agrega una opción para salir al final del menú.
     */
    public void agregarOpcionSalir() {
        optionList.add("Salir");
    }

    /**
     * clearMenu elimina todas las opciones y las acciones asociadas del menú.
     */
    public void limpiarMenu() {
        optionList.clear();
        menuActionPrototypeList.clear();
    }

    /**
     * mostrarMenu muestra las opciones dentro del menú.
     */
    public void mostrarMenu() {
        System.out.println(BIENVENIDA_MENU);
        for (int i = 0; i < optionList.size(); i++) {
            System.out.println(i + 1 + ") " + optionList.get(i));
        }
    }

    public void solicitarOpcion(){
        System.out.print(SOLICITAR_OPCION);
    }

    /**
     * leerOpcion lee la entrada del usuario y valida que se haya proporcionado en un formato apropiado.
     * @return opcion: un número que representa el índice de una opción específica.
     */
    public int leerOpcion() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            try {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                if (opcion < 0 || opcion > optionList.size()) {
                    System.out.print(OPCION_NO_DISPONIBLE);
                    continue;
                }
                else if(opcion == 0){
                    killMenu();
                    opcion = optionList.size();
                }
                return opcion;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.print(ERROR_TIPO_NUMERICO);
            }
        }
    }

    /**
     * ejecutarOpcion selecciona y ejecuta la función asociada a la opción elegida por el usuario.
     * @param opcion: el número que representa el índice de la opción seleccionada por el usuario.
     */
    public void ejecutarOpcion(int opcion) {
        if (opcion == optionList.size()) {
            killMenu();
            System.out.println(FIN_DEL_PROGRAMA);
            return;
        }
        menuActionPrototypeList.get(opcion - 1).definedAction();
    }
}
