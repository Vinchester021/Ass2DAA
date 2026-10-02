package com.example.daa;

public class MinHeap {

    public int size() {
        return size;
    }

    private int[] data;
    private int size;
    private final Metrics metrics;

    public MinHeap(Metrics metrics) {
        data = new int[10];
        size = 0;
        this.metrics = metrics;
    }

    public void insert(int x) {
        if (size == data.length) {
            grow();
        }

        data[size] = x;
        bubbleUp(size);
        size++;
    }

    private void grow() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            metrics.addStep();
            metrics.addMove();

            newData[i] = data[i];
        }

        data = newData;
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            metrics.addStep();
            metrics.addStep();
            metrics.addComparison();

            if (data[parent] <= data[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void swap(int i, int j) {
        metrics.addStep();
        metrics.addStep();

        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;

        metrics.addMove();
        metrics.addMove();
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        metrics.addStep();
        return data[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        metrics.addStep();
        int min = data[0];

        metrics.addStep();
        data[0] = data[size - 1];
        metrics.addMove();

        size--;

        if (size > 0) {
            bubbleDown(0);
        }

        return min;
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size) {
                metrics.addStep();
                metrics.addStep();
                metrics.addComparison();

                if (data[left] < data[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                metrics.addStep();
                metrics.addStep();
                metrics.addComparison();

                if (data[right] < data[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }


}