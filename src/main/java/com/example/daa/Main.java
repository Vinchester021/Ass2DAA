package com.example.daa;

public class Main {

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        list.add(1, 99);
        System.out.println();
        System.out.println("Size - " + list.size());
        System.out.println("Index 0 - " + list.get(0));
        System.out.println("Index 1 - " + list.get(1));
        System.out.println("Index 2 - " + list.get(2));
        System.out.println("Index 3 - " + list.get(3));
        System.out.println();
        System.out.println("Contains 20 - " + list.contains(20));
        System.out.println("Contains 50 - " + list.contains(50));
        System.out.println();

        int removed = list.remove(1);

        System.out.println("Removed - " + removed);
        System.out.println("New size - " + list.size());
        System.out.println("Index 1 after remove - " + list.get(1));
    }
}