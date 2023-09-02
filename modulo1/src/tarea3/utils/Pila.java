/**
 * @autor: Eduardo Orozco Osuna
 */

package tarea3.utils;

import java.util.Optional;

/**
 * A generic stack data structure implemented using a linked list.
 * @param <E> the type of elements held in this stack
 */
public class Pila<E> {
    private MiListaEnlazada<E> stack;

    /**
     * Constructs an empty stack.
     */
    public Pila() {
        stack = new MiListaEnlazada<>();
    }

    /**
     * Pushes an element onto the top of this stack.
     * @param elmtToPush the element to be pushed onto this stack
     */
    public void push(E elmtToPush) {
        stack.agregarPrimero(elmtToPush);
    }

    /**
     * Retrieves, but does not remove, the element at the top of this stack.
     * @return an Optional containing the element at the top of the stack, or an empty Optional if the stack is empty
     */
    public Optional<E> peek() {
        return stack.get(0);
    }

    /**
     * Removes and returns the element at the top of this stack.
     * @return an Optional containing the element removed from the top of the stack, or an empty Optional if the stack is empty
     */
    public Optional<E> pop() {
        Optional<E> popped = stack.get(0);
        stack.remove(popped.get());
        return popped;
    }

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in this stack
     */
    public int getSize() {
        return stack.getSize();
    }

    /**
     * Checks if this stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return !(stack.getSize() > 0);
    }

    /**
     * Returns a new stack with elements in reverse order compared to this stack.
     * @return a new stack with elements in reverse order
     */
    public Pila<E> inverse() {
        Pila<E> inversed = new Pila<>();

        for (int i = 0; i < stack.getSize(); ++i) {
            inversed.push(stack.get(i).get());
        }

        return inversed;
    }

    /**
     * Returns a new stack with elements alternated between this stack and another stack.
     * @param stackB the other stack to alternate elements with
     * @return a new stack with alternated elements
     */
    public Pila<E> alternate(Pila<E> stackB) {
        Pila<E> alternated = new Pila<>();

        for (int i = 0; i < Math.max(stack.getSize(), stackB.getSize()); ++i) {
            try {
                alternated.push(stackB.stack.get(stack.getSize() - i - 1).get());
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                alternated.push(stack.get(stack.getSize() - i - 1).get());
            } catch (IndexOutOfBoundsException e) {
            }
        }

        return alternated;
    }
}
