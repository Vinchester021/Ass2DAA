package com.example.daa;

public class MyLinkedList {

    private Node head;
    private int size;
    private final Metrics metrics;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public MyLinkedList(Metrics metrics) {
        head = null;
        size = 0;
        this.metrics = metrics;
    }

    public void add(int x) {
        Node newNode = new Node(x);

        if (head == null) {
            head = newNode;
            metrics.addMove();
        }
        else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
                metrics.addStep();
            }

            current.next = newNode;
            metrics.addMove();
        }

        size++;
    }

    public int get(int index) {
        checkIndex(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            metrics.addStep();
        }

        return current.value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + " Size: " + size
            );
        }
    }

    public boolean contains(int x) {
        Node current = head;

        while (current != null) {
            metrics.addComparison();

            if (current.value == x) {
                return true;
            }

            current = current.next;
            metrics.addStep();
        }
        return false;
    }

    public void add(int index, int x) {
        checkAddIndex(index);

        Node newNode = new Node(x);

        if (index == 0) {
            newNode.next = head;
            metrics.addMove();

            head = newNode;
            metrics.addMove();
        }
        else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                metrics.addStep();
            }

            newNode.next = current.next;
            metrics.addMove();

            current.next = newNode;
            metrics.addMove();
        }

        size++;
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + " Size: " + size
            );
        }
    }

    public int remove(int index) {
        checkIndex(index);

        int removedValue;

        if (index == 0) {
            removedValue = head.value;

            head = head.next;
            metrics.addMove();
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                metrics.addStep();
            }

            removedValue = current.next.value;

            current.next = current.next.next;
            metrics.addMove();
        }

        size--;

        return removedValue;
    }

    public int size() {
        return size;
    }
}