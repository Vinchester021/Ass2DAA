package com.example.daa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RandomDataTest {

    @Test
    void DARandomTest() {
        Metrics metrics = new Metrics();
        DA array = new DA(metrics);

        ArrayList<Integer> expected = new ArrayList<>();

        Random random = new Random(42);

        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(1000);

            array.add(value);
            expected.add(value);
        }

        assertEquals(expected.size(), array.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), array.get(i));
        }
    }

    @Test
    void linkedListRandomTest() {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        ArrayList<Integer> expected = new ArrayList<>();

        Random random = new Random(42);

        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(1000);

            list.add(value);
            expected.add(value);
        }

        assertEquals(expected.size(), list.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), list.get(i));
        }
    }
}