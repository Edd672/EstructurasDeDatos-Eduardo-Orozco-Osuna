package tarea4.ejercicio_2.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menú proporciona un conjunto de variables y miembros que esbozan una plantilla de menú de usuario.
 */
public class Menu {
    /**
     * Textos que pueden aparecer al interactuar con el menú.
     */
    private String BIENVENIDA_MENU = "Menú";
    private String SOLICITUD_OPCION = "Digite el número correspondiente a la opción a elegir: ";
    private String ERROR_TIPO_NUMÉRICO = "El valor ingresado no tiene un formato numérico. Intente de nuevo: ";
    private String ERROR_FUERA_DE_RANGO = "Opción no disponible. Intente de nuevo: ";
    private String FIN_DEL_PROGRAMA = "Programa finalizado.";

    /**
     * optionList almacena las cadenas que corresponden a las opciones mostradas al usuario + una opción de salida.
     * menuActionPrototypeList almacena las funciones asociadas a cada opción mostrada al usuario, excepto la opción de salida.
     */
    private ArrayList<String> listaDeOpciones = new ArrayList<>();
    private ArrayList<MenuActionPrototype> listaDePrototiposDeAcción = new ArrayList<>();

    /**
     * isActive establece el estado de disponibilidad del menú para el usuario y ayuda a determinar si el menú debe seguir mostrándose o no.
     */
    private boolean activo = true;

    /**
     * terminarMenú indica que el menú de usuario debe dejar de mostrarse al usuario.
     */
    public void terminarMenú() {
        activo = false;
    }

    /**
     * estáActivo determina el estado de disponibilidad del menú para el usuario.
     */
    public boolean estáActivo() {
        return activo;
    }

    /**
     * agregarOpción añade una opción de menú para que el menú de usuario la proporcione y las acciones asociadas a cada una.
     * @param opción: un texto que contiene una de las opciones del menú.
     * @param prototipoDeAcción: el método que se ejecutará al seleccionar la opción asociada a ella.
     * @return this: el menú actual con sus modificaciones correspondientes.
     */
    public Menu agregarOpción(String opción, MenuActionPrototype prototipoDeAcción) {
        listaDeOpciones.add(opción);
        listaDePrototiposDeAcción.add(prototipoDeAcción);
        return this;
    }

    /**
     * agregarOpciónSalida añade un método de salida al final del menú.
     */
    public void agregarOpciónSalida() {
        listaDeOpciones.add("Salir");
    }

    /**
     * limpiarMenú elimina todas las opciones y las acciones asociadas a ellas del menú.
     */
    public void limpiarMenú() {
        listaDeOpciones.clear();
        listaDePrototiposDeAcción.clear();
    }

    /**
     * mostrarMenú muestra las opciones en el menú.
     */
    public void mostrarMenú() {
        System.out.println(BIENVENIDA_MENU);
        for (int i = 0; i < listaDeOpciones.size(); i++) {
            System.out.println(i + 1 + ") " + listaDeOpciones.get(i));
        }
    }

    public void solicitarOpción(){
        System.out.print(SOLICITUD_OPCION);
    }

    /**
     * leerOpción lee la entrada del usuario y valida que se haya proporcionado en un formato apropiado.
     * @return opción: un número que representa el índice de una opción específica.
     */
    public int leerOpción() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            try {
                int opción = entrada.nextInt();
                entrada.nextLine();
                if (opción < 0 || opción > listaDeOpciones.size()) {
                    System.out.print(ERROR_FUERA_DE_RANGO);
                    continue;
                }
                else if(opción == 0){
                    terminarMenú();
                    opción = listaDeOpciones.size();
                }
                return opción;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.print(ERROR_TIPO_NUMÉRICO);
            }
        }
    }

    /**
     * ejecutarOpción ejecuta la función asociada a la opción seleccionada por el usuario.
     * @param opción: el número que representa el índice de la opción seleccionada por el usuario.
     */
    public void ejecutarOpción(int opción) {
        if (opción == listaDeOpciones.size()) {
            terminarMenú();
            System.out.println(FIN_DEL_PROGRAMA);
            return;
        }
        listaDePrototiposDeAcción.get(opción - 1).accion();
    }
}
