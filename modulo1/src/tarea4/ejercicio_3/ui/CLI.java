package tarea4.ejercicio_3.ui;

import tarea4.utils.Cola;
import tarea4.ejercicio_3.process.SimuladorServidor;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

/**
 * Autor: Eduardo Oroxco Osuna
 */
public class CLI {
    private static Cola<Integer> servidorA = new Cola<>();
    private static Cola<Integer> servidorB = new Cola<>();
    private static Scanner entrada = new Scanner(System.in);

    private static Menu menu = new Menu();

    private static String SIMULAR_SERVIDORES = "Simular trabajo simultáneo de dos servidores.";

    private static String EJECUTAR_PROCESO_SERVIDOR_A = "Ejecutando proceso del servidor A.\n";
    private static String EJECUTAR_PROCESO_SERVIDOR_B = "Ejecutando proceso del servidor B.\n";
    private static String SIN_PROCESOS_EN_SERVIDOR = "¡No hay más procesos para ejecutar en el servidor!.\n";

    private static MenuActionPrototype simularServidores = () -> {
        int i;
        double probabilidad;
        Random aleatorio = new Random();
        Optional<Integer> resultado;

        for (i = 0; i < SimuladorServidor.obtenerNumeroDeProcesos(); ++i) {
            SimuladorServidor.cargarServidor(servidorA, i);
            SimuladorServidor.cargarServidor(servidorB, i);
        }

        while (!servidorA.estaVacia() || !servidorB.estaVacia()) {
            probabilidad = aleatorio.nextDouble();
            if (probabilidad <= 0.3) {
                System.out.printf(EJECUTAR_PROCESO_SERVIDOR_A);
            } else {
                System.out.printf(EJECUTAR_PROCESO_SERVIDOR_B);
            }

            resultado = SimuladorServidor.procesarProceso(servidorA, servidorB, probabilidad);
            if (resultado != null) {
                System.out.println(resultado.get());
            } else {
                System.out.printf(SIN_PROCESOS_EN_SERVIDOR);
            }
        }
    };

    static {
        menu
                .agregarOpcion(SIMULAR_SERVIDORES, simularServidores)
                .agregarOpcionSalir();
    }

    public static void iniciarAplicacion() {
        do {
            menu.mostrarMenu();
            menu.solicitarOpcion();
            int opcion = menu.leerOpcion();
            menu.ejecutarOpcion(opcion);
        } while (menu.estaActivo());
    }
}
