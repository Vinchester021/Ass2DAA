package com.example.daa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void addAndGetTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void addAtIndexTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(10);
        list.add(30);
        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void removeTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(10);
        list.add(20);
        list.add(30);

        int removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @Test
    void containsTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(10);
        list.add(20);

        assertTrue(list.contains(20));
        assertFalse(list.contains(50));
    }

    @Test
    void duplicatesTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(5);
        list.add(5);
        list.add(5);

        assertEquals(3, list.size());
        assertTrue(list.contains(5));
    }

    @Test
    void firstAndLastIndexTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(2));
    }

    @Test
    void invalidIndexTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(0)
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(0)
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(1, 10)
        );
    }
}