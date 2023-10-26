package utils.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Implementación básica de un mapa genérico.
 * @param <Clave> El tipo de las claves.
 * @param <Valor> El tipo de los valores.
 */
public class HashMap<Clave, Valor> {
    private static final int CAPACIDAD_INICIAL = 16;
    private ArrayList<LinkedList<Entrada<Clave, Valor>>> compartimentos;
    private int tamaño;

    /**
     * Construye un nuevo mapa con la capacidad inicial especificada.
     */
    public HashMap() {
        compartimentos = new ArrayList<>(CAPACIDAD_INICIAL);
        for (int i = 0; i < CAPACIDAD_INICIAL; i++) {
            compartimentos.add(new LinkedList<>());
        }
        tamaño = 0;
    }

    private int calcularÍndice(Clave key) {
        int hash = key.hashCode();
        return Math.abs(hash) % compartimentos.size();
    }

    /**
     * Asocia el valor especificado con la clave especificada en este mapa.
     *
     * @param clave La clave con la que se asociará el valor.
     * @param valor El valor que se asociará con la clave.
     */
    public void agregar(Clave clave, Valor valor) {
        int índice = calcularÍndice(clave);
        LinkedList<Entrada<Clave, Valor>> compartimento = compartimentos.get(índice);

        for (Entrada<Clave, Valor> entrada : compartimento) {
            if (entrada.obtenerClave().equals(clave)) {
                entrada.establecerValor(valor);
                return;
            }
        }
        compartimento.add(new Entrada<>(clave, valor));
        tamaño++;
    }

    /**
     * Obtiene el valor asociado a la clave especificada en este mapa.
     *
     * @param clave La clave cuyo valor se quiere obtener.
     * @return El valor asociado a la clave o null si no hay ninguna asociación.
     */
    public Valor obtener(Clave clave) {
        int índice = calcularÍndice(clave);
        LinkedList<Entrada<Clave, Valor>> compartimento = compartimentos.get(índice);

        for (Entrada<Clave, Valor> entrada : compartimento) {
            if (entrada.obtenerClave().equals(clave)) {
                return entrada.obtenerValor();
            }
        }

        return null;
    }

    /**
     * Obtiene una lista de claves en el mapa.
     *
     * @return Una lista de claves en el mapa.
     */
    public ArrayList<Clave> obtenerClaves() {
        ArrayList<Clave> claves = new ArrayList<>();

        for (LinkedList<Entrada<Clave, Valor>> compartimento : compartimentos) {
            for (Entrada<Clave, Valor> entrada : compartimento) {
                claves.add(entrada.obtenerClave());
            }
        }

        return claves;
    }

    /**
     * Verifica si este mapa contiene una asociación para la clave especificada.
     *
     * @param clave La clave a verificar.
     * @return True si este mapa contiene una asociación para la clave, false en caso contrario.
     */
    public boolean contieneClave(Clave clave) {
        int índice = calcularÍndice(clave);
        LinkedList<Entrada<Clave, Valor>> compartimento = compartimentos.get(índice);

        for (Entrada<Clave, Valor> entrada : compartimento) {
            if (entrada.obtenerClave().equals(clave)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Elimina la asociación de la clave especificada en este mapa, si existe.
     *
     * @param clave La clave cuya asociación se eliminará.
     */
    public void eliminar(Clave clave) {
        int índice = calcularÍndice(clave);
        LinkedList<Entrada<Clave, Valor>> compartimento = compartimentos.get(índice);
        Entrada<Clave, Valor> entradaAEliminar = null;

        for (Entrada<Clave, Valor> entrada : compartimento) {
            if (entrada.obtenerClave().equals(clave)) {
                entradaAEliminar = entrada;
                break;
            }
        }
        if (entradaAEliminar != null) {
            compartimento.remove(entradaAEliminar);
            tamaño--;
        }
    }

    /**
     * Obtiene la cantidad de asociaciones clave-valor en este mapa.
     *
     * @return La cantidad de asociaciones clave-valor en este mapa.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Verifica si el mapa está vacío.
     *
     * @return True si el mapa está vacío, false en caso contrario.
     */
    public boolean estáVacío() {
        return tamaño() > 0 ? false : true;
    }
}

