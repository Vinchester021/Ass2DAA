package com.example.daa;

public class Main {

    public static void main(String[] args) {

        Metrics metrics = new Metrics();
        MinHeap heap = new MinHeap(metrics);

        heap.insert(12);
        heap.insert(4);
        heap.insert(9);
        heap.insert(2);
        heap.insert(15);

        System.out.println();
        System.out.println("Min - " + heap.peekMin());
        System.out.println();

        while (heap.size() > 0) {
            System.out.println(heap.extractMin());
        }

        System.out.println();
        System.out.println("Steps - " + metrics.getSteps());
        System.out.println("Moves - " + metrics.getMoves());
        System.out.println("Comparisons - " + metrics.getComparisons());
    }
}