package com.example.daa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DATest {

    @Test
    void addAndGetTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(3, array.size());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void addAtIndexTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        array.add(10);
        array.add(30);
        array.add(1, 20);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void removeTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        array.add(10);
        array.add(20);
        array.add(30);

        int removed = array.remove(1);

        assertEquals(20, removed);
        assertEquals(2, array.size());
        assertEquals(30, array.get(1));
    }

    @Test
    void containsTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        array.add(10);
        array.add(20);

        assertTrue(array.contains(20));
        assertFalse(array.contains(50));
    }

    @Test
    void duplicatesTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        array.add(5);
        array.add(5);
        array.add(5);

        assertEquals(3, array.size());
        assertTrue(array.contains(5));
    }

    @Test
    void invalidIndexTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.get(0)
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.remove(0)
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.add(1, 10)
        );
    }
}