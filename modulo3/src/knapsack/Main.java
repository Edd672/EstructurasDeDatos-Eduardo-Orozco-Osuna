package knapsack;

import utils.knapsack.Articulo;

import static utils.knapsack.Knapsack.mochila01;

public class Main {
    public static void main(String[] args) {
        Articulo[] articulos = {
                new Articulo(2, 10),
                new Articulo(5, 20),
                new Articulo(9, 30)
        };

        int capacidadMochila = 10;

        int valorMaximo = mochila01(capacidadMochila, articulos);
        System.out.println("El valor máximo que se puede obtener es: " + valorMaximo);
    }
}
