
package tarea4.ejercicio_3.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Autor: Eduardo Oroxco Osuna
 */
public class Menu {
    private String BIENVENIDA_MENU = "Menú";
    private String SOLICITAR_OPCION = "Elija el número correspondiente a la opción que desea: ";
    private String ERROR_TIPO_NUMERICO = "El valor ingresado no es numérico. Intente nuevamente: ";
    private String ERROR_FUERA_DE_RANGO = "Opción no válida. Intente nuevamente: ";
    private String FIN_DEL_PROGRAMA = "Programa finalizado.";

    private ArrayList<String> listaOpciones = new ArrayList<>();
    private ArrayList<MenuActionPrototype> listaAccionesMenu = new ArrayList<>();

    private boolean activo = true;

    public void desactivarMenu() {
        activo = false;
    }

    public boolean estaActivo() {
        return activo;
    }

    public Menu agregarOpcion(String opcion, MenuActionPrototype accionMenu) {
        listaOpciones.add(opcion);
        listaAccionesMenu.add(accionMenu);
        return this;
    }

    public void agregarOpcionSalir() {
        listaOpciones.add("Salir");
    }

    public void limpiarMenu() {
        listaOpciones.clear();
        listaAccionesMenu.clear();
    }

    public void mostrarMenu() {
        System.out.println(BIENVENIDA_MENU);
        for (int i = 0; i < listaOpciones.size(); i++) {
            System.out.println(i + 1 + ") " + listaOpciones.get(i));
        }
    }

    public void solicitarOpcion() {
        System.out.print(SOLICITAR_OPCION);
    }

    public int leerOpcion() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            try {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                if (opcion < 0 || opcion > listaOpciones.size()) {
                    System.out.print(ERROR_FUERA_DE_RANGO);
                    continue;
                } else if (opcion == 0) {
                    desactivarMenu();
                    opcion = listaOpciones.size();
                }
                return opcion;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.print(ERROR_TIPO_NUMERICO);
            }
        }
    }

    public void ejecutarOpcion(int opcion) {
        if (opcion == listaOpciones.size()) {
            desactivarMenu();
            System.out.println(FIN_DEL_PROGRAMA);
            return;
        }
        listaAccionesMenu.get(opcion - 1).accion();
    }
}

