package com.example.daa;

public class Main {

    public static void main(String[] args) {

        MinHeap heap = new MinHeap();

        heap.insert(12);
        heap.insert(4);
        heap.insert(9);
        heap.insert(2);
        heap.insert(15);

        System.out.println();
        System.out.println("Size - " + heap.size());
        System.out.println("Min - " + heap.peekMin());
        System.out.println();
        System.out.println("Extracted - " + heap.extractMin());
        System.out.println("New min - " + heap.peekMin());
        System.out.println();
        System.out.println("All elements:");

        while (heap.size() > 0) {
            System.out.println(heap.extractMin());
        }
    }
}