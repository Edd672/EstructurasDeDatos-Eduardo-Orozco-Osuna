package tarea11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.collection.HashMap;

public class HashMapTest {
    private HashMap<String, Integer> hashMap;

    @BeforeEach
    public void setUp() {
        hashMap = new HashMap<>();
    }

    @Test
    public void testTamano() {
        hashMap.agregar("One", 1);
        assertEquals(1, hashMap.tamaño());
    }

    @Test
    public void testObtener() {
        hashMap.agregar("Two", 2);
        assertEquals(2, hashMap.obtener("Two"));
    }

    @Test
    public void testContieneClave() {
        hashMap.agregar("Three", 3);
        assertTrue(hashMap.contieneClave("Three"));
    }

    @Test
    public void testEliminar() {
        hashMap.agregar("Four", 4);
        hashMap.eliminar("Four");
        assertNull(hashMap.obtener("Four"));
    }

    @Test
    public void testObtenerClaves() {
        hashMap.agregar("Eight", 8);
        hashMap.agregar("Nine", 9);
        hashMap.agregar("Ten", 10);
        assertEquals(3, hashMap.obtenerClaves().size());
        assertTrue(hashMap.obtenerClaves().contains("Eight"));
        assertTrue(hashMap.obtenerClaves().contains("Nine"));
        assertTrue(hashMap.obtenerClaves().contains("Ten"));
    }

}
