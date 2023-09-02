/**
 * @autor: Eduardo Orozco Osuna
 */

package tarea3.process;

import tarea3.utils.Pila;

public class Expresiones {
    /**
     * Convierte una cadena de entrada en una pila de subcadenas.
     *
     * @param cadena La cadena de entrada a convertir.
     * @return Una pila de subcadenas analizadas desde la cadena de entrada.
     */
    public static Pila<String> cadenaAStack(String cadena) {
        Pila<String> pila = new Pila<>();
        StringBuilder subcadenaActual = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char ch = cadena.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                subcadenaActual.append(ch);
            } else {
                if (subcadenaActual.length() > 0) {
                    pila.push(subcadenaActual.toString());
                    subcadenaActual.setLength(0);
                }
                pila.push(String.valueOf(ch));
            }
        }

        if (subcadenaActual.length() > 0) {
            pila.push(subcadenaActual.toString());
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
     * Convierte una cadena de entrada que representa una expresión matemática infija en su equivalente en notación postfija.
     *
     * @param infija La expresión matemática infija.
     * @return La representación matemática postfija de la expresión de entrada.
     */
    public static String cadenaInfijaAPostfija(String infija) {
        StringBuilder postfija = new StringBuilder();
        Pila<Character> pila = new Pila<>();

        for (char c : infija.toCharArray()) {
            if (Character.isDigit(c)) {
                postfija.append(c);
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek().get() != '(') {
                    postfija.append(pila.pop().get());
                }
                pila.pop().get(); // Sacar el '('
            } else {
                while (!pila.isEmpty() && precedencia(pila.peek().get()) >= precedencia(c)) {
                    postfija.append(pila.pop().get());
                }
                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            postfija.append(pila.pop().get());
        }

        return postfija.toString();
    }

    /**
     * Convierte una pila de entrada que representa una expresión matemática infija en su equivalente en notación postfija.
     *
     * @param infija La expresión matemática infija.
     * @return La representación matemática postfija de la expresión de entrada.
     */
    public static Pila<String> stackInfijaAPostfija(Pila<String> infija) {
        Pila<Character> stack = new Pila<>();
        Pila<String> postfix = new Pila<>();

        while (!infija.isEmpty()) {
            String element = infija.pop().get();
            char c = element.charAt(0);

            if (Character.isDigit(c)) {
                postfix.push(element);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek().get() != '(') {
                    postfix.push(String.valueOf(stack.pop().get()));
                }
                stack.pop(); // Pop the '('
            } else {
                while (!stack.isEmpty() && precedencia(stack.peek().get()) >= precedencia(c)) {
                    postfix.push(String.valueOf(stack.pop().get()));
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.push(String.valueOf(stack.pop().get()));
        }

        postfix = postfix.inverse();
        return postfix;
    }

    /**
     * Calcula el resultado de evaluar la expresión postfija dada.
     *
     * @param pilaExpresion La expresión postfija.
     * @return El resultado de evaluar la expresión postfija dada.
     */
    public static double calcularExpresionPostfija(Pila<String> pilaExpresion) {
        Pila<Double> respuesta = new Pila<>();

        while (!pilaExpresion.isEmpty()) {
            String elemento = pilaExpresion.pop().get();
            if (elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/")) {
                double operando2 = respuesta.pop().get();
                double operando1 = respuesta.pop().get();
                double resultado = realizarOperacion(operando1, operando2, elemento);
                respuesta.push(resultado);
            } else {
                respuesta.push(Double.parseDouble(elemento));
            }
        }

        return respuesta.pop().get();
    }

    private static double realizarOperacion(double operando1, double operando2, String operador) {
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
}
