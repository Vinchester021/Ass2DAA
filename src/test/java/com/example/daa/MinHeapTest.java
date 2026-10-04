package com.example.daa;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    private boolean isValidHeap(MinHeap heap) throws Exception {

        Field dataField = MinHeap.class.getDeclaredField("data");
        Field sizeField = MinHeap.class.getDeclaredField("size");

        dataField.setAccessible(true);
        sizeField.setAccessible(true);

        int[] data = (int[]) dataField.get(heap);
        int size = (int) sizeField.get(heap);

        for (int i = 0; i < size; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && data[i] > data[left]) {
                return false;
            }

            if (right < size && data[i] > data[right]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void heapPropertyAfterOperationsTest() throws Exception {

        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        int[] values = {
                12, 4, 9, 2, 15, 1, 20, 7, 3, 18
        };

        for (int value : values) {
            heap.insert(value);

            assertTrue(isValidHeap(heap));
        }

        while (heap.size() > 0) {
            heap.extractMin();

            assertTrue(isValidHeap(heap));
        }
    }


    @Test
    void insertAndPeekMinTest() {
        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(2);

        assertEquals(4, heap.size());
        assertEquals(2, heap.peekMin());
    }

    @Test
    void extractMinTest() {
        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(2);

        assertEquals(2, heap.extractMin());
        assertEquals(5, heap.peekMin());
        assertEquals(3, heap.size());
    }

    @Test
    void duplicatesTest() {
        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        heap.insert(5);
        heap.insert(5);
        heap.insert(5);

        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void emptyHeapTest() {
        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        assertThrows(
                IllegalStateException.class,
                heap::peekMin
        );

        assertThrows(
                IllegalStateException.class,
                heap::extractMin
        );
    }

    @Test
    void sortedOutputTest() {
        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        int[] values = {
                12, 4, 9, 2, 15, 1, 20, 7
        };

        for (int value : values) {
            heap.insert(value);
        }

        int previous = Integer.MIN_VALUE;

        while (heap.size() > 0) {
            int current = heap.extractMin();

            assertTrue(current >= previous);

            previous = current;
        }
    }
}