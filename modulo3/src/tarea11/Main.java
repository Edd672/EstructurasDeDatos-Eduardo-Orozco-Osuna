package tarea11;

import utils.collection.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> myHashMap = new HashMap<>();

        myHashMap.agregar("AAA", 100);
        myHashMap.agregar("BBB", 200);
        myHashMap.agregar("CCC", 300);

        for(String clave : myHashMap.obtenerClaves()){
            System.out.println(clave);
        }
    }
}
