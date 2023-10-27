/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea3.process;

import tarea3.utils.Pila;

public class Notaciones {
    /**
     * Convierte una cadena de entrada dada en una pila de subcadenas.
     *
     * @param str La cadena de entrada que se convertirá.
     * @return Una pila de subcadenas analizadas a partir de la cadena de entrada.
     */
    public static Pila<String> cadenaAPila(String str) {
        Pila<String> pila = new Pila<>();
        StringBuilder subcadenaActual = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char carácter = str.charAt(i);

            if (Character.isDigit(carácter) || carácter == '.') {
                subcadenaActual.append(carácter);
            } else {
                if (subcadenaActual.length() > 0) {
                    pila.empujar(subcadenaActual.toString());
                    subcadenaActual.setLength(0);
                }
                pila.empujar(String.valueOf(carácter));
            }
        }

        if (subcadenaActual.length() > 0) {
            pila.empujar(subcadenaActual.toString());
        }

        return pila;
    }

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Convierte una cadena de entrada matemática en notación infija en su equivalente en notación postfija.
     *
     * @param infijo La expresión matemática en notación infija.
     * @return La representación matemática en notación postfija de la expresión de entrada.
     */
    public static String cadenaInfijaAPostfija(String infijo) {
        StringBuilder postfija = new StringBuilder();
        Pila<Character> pila = new Pila<>();

        for (char c : infijo.toCharArray()) {
            if (Character.isDigit(c)) {
                postfija.append(c);
            } else if (c == '(') {
                pila.empujar(c);
            } else if (c == ')') {
                while (!pila.estáVacía() && pila.vistazo().get() != '(') {
                    postfija.append(pila.pop().get());
                }
                pila.pop(); // Eliminar el '('
            } else {
                while (!pila.estáVacía() && precedencia(pila.vistazo().get()) >= precedencia(c)) {
                    postfija.append(pila.pop().get());
                }
                pila.empujar(c);
            }
        }

        while (!pila.estáVacía()) {
            postfija.append(pila.pop().get());
        }

        return postfija.toString();
    }

    /**
     * Convierte una pila de entrada que representa una expresión matemática en notación infija en su equivalente en notación postfija.
     *
     * @param infijo La expresión matemática en notación infija.
     * @return La representación matemática en notación postfija de la expresión de entrada.
     */
    public static Pila<String> pilaInfijaAPostfija(Pila<String> infijo) {
        Pila<Character> pila = new Pila<>();
        Pila<String> postfija = new Pila<>();

        while (!infijo.estáVacía()) {
            String elemento = infijo.pop().get();
            char c = elemento.charAt(0);

            if (Character.isDigit(c)) {
                postfija.empujar(elemento);
            } else if (c == '(') {
                pila.empujar(c);
            } else if (c == ')') {
                while (!pila.estáVacía() && pila.vistazo().get() != '(') {
                    postfija.empujar(String.valueOf(pila.pop().get()));
                }
                pila.pop(); // Eliminar el '('
            } else {
                while (!pila.estáVacía() && precedencia(pila.vistazo().get()) >= precedencia(c)) {
                    postfija.empujar(String.valueOf(pila.pop().get()));
                }
                pila.empujar(c);
            }
        }

        while (!pila.estáVacía()) {
            postfija.empujar(String.valueOf(pila.pop().get()));
        }

        postfija = postfija.inversa();
        return postfija;
    }

    private static double realizarOperación(double operando1, double operando2, String operador) {
        switch (operador) {
            case "+":
                return operando1 + operando2;
            case "-":
                return operando1 - operando2;
            case "*":
                return operando1 * operando2;
            case "/":
                return operando1 / operando2;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    /**
     * Calcula el resultado de evaluar la expresión en notación postfija dada.
     *
     * @param expresiónPostfija La expresión matemática en notación postfija.
     * @return El resultado de evaluar la expresión en notación postfija dada.
     */
    public static double evaluarExpresiónPostfija(Pila<String> expresiónPostfija) {
        Pila<Double> resultado = new Pila<>();

        while (!expresiónPostfija.estáVacía()) {
            String elemento = expresiónPostfija.pop().get();
            if (elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/")) {
                double operando2 = resultado.pop().get();
                double operando1 = resultado.pop().get();
                double resultadoOperación = realizarOperación(operando1, operando2, elemento);
                resultado.empujar(resultadoOperación);
            } else {
                resultado.empujar(Double.parseDouble(elemento));
            }
        }

        return resultado.pop().get();
    }
}
