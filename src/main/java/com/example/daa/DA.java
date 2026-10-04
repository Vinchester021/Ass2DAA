package com.example.daa;

public class DA {

    private int[] data;
    private int size;
    private final Metrics metrics;

    public DA(Metrics metrics) {
        data = new int[10];
        size = 0;
        this.metrics = metrics;
    }

    public void add(int x) {
        if (size == data.length) {
            grow();
        }

        data[size] = x;
        size++;
    }

    public void add(int index, int x) {
        checkAddIndex(index);

        if (size == data.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];

            metrics.addStep();
            metrics.addMove();
        }

        data[index] = x;
        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        metrics.addStep();
        int removedValue = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];

            metrics.addStep();
            metrics.addMove();
        }

        size--;

        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        metrics.addStep();
        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {

            metrics.addStep();
            metrics.addComparison();

            if (data[i] == x) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    private void grow() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];

            metrics.addStep();
            metrics.addMove();
        }

        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + " Size: " + size
            );
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + " Size: " + size
            );
        }
    }


}