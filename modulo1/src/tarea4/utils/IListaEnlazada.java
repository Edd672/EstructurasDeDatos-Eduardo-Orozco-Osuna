/**
 * @author Axel Balam Mancera Miramontes
 */


package tarea4.utils;
import java.util.Optional;

/**
 * Interfaz base para la implementación de listas enlazadas
 * */
public interface IListaEnlazada<E>{
    void agregar(E e);
    void eliminar(E e);
    Optional<E> obtener(int index);
    void actualizar(E oldValue, E newValue);
    int obtenerTamaño();
    boolean contiene(E t);
}