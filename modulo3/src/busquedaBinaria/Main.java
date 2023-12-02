package busquedaBinaria;

import utils.busquedaBinaria.BusquedaBinaria;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int elementoBuscar = 7;
        int indiceEncontrado = BusquedaBinaria.buscarConInterpolacion(array, elementoBuscar);

        if (indiceEncontrado != -1) {
            System.out.println("El elemento " + elementoBuscar + " se encuentra en la posición " + indiceEncontrado + ".");
        } else {
            System.out.println("El elemento " + elementoBuscar + " no se encuentra en el array.");
        }
    }
}
