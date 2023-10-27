
/**
 * Autor: Eduardo Oroxco Osuna
 */
package tarea3.ui;

/**
 * Una instancia de la interfaz funcional MenuActionPrototype puede recibir la definición de una función lambda.
 */
@FunctionalInterface
public interface MenuActionPrototype {
    /**
     * definedAction es el prototipo del método que se utilizará para inicializar una instancia de la clase MenuActionPrototype como una función lambda definida.
     */
    void definedAction();
}
