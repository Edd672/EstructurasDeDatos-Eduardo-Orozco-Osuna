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
    public void testAddAndGetSize() {
        hashMap.add("One", 1);
        assertEquals(1, hashMap.size());
    }

    @Test
    public void testAddAndGet() {
        hashMap.add("Two", 2);
        assertEquals(2, hashMap.get("Two"));
    }

    @Test
    public void testAddAndContainsKey() {
        hashMap.add("Three", 3);
        assertTrue(hashMap.containsKey("Three"));
    }

    @Test
    public void testAddAndRemove() {
        hashMap.add("Four", 4);
        hashMap.remove("Four");
        assertNull(hashMap.get("Four"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashMap.isEmpty());
        hashMap.add("Five", 5);
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void testClear() {
        hashMap.add("Six", 6);
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }

    @Test
    public void testAddDuplicateKey() {
        hashMap.add("Seven", 7);
        hashMap.add("Seven", 77);
        assertEquals(77, hashMap.get("Seven"));
    }

    @Test
    public void testGetKeys() {
        hashMap.add("Eight", 8);
        hashMap.add("Nine", 9);
        hashMap.add("Ten", 10);
        assertEquals(3, hashMap.getKeys().size());
        assertTrue(hashMap.getKeys().contains("Eight"));
        assertTrue(hashMap.getKeys().contains("Nine"));
        assertTrue(hashMap.getKeys().contains("Ten"));
    }

    @Test
    public void testGetValues() {
        hashMap.add("Eleven", 11);
        hashMap.add("Twelve", 12);
        hashMap.add("Thirteen", 13);
        assertEquals(3, hashMap.getValues().size());
        assertTrue(hashMap.getValues().contains(11));
        assertTrue(hashMap.getValues().contains(12));
        assertTrue(hashMap.getValues().contains(13));
    }
}
