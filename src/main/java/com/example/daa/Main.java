package com.example.daa;

public class Main {

    public static void main(String[] args) {

        DA arr = new DA();

        arr.add(10);
        arr.add(20);
        arr.add(30);

        arr.add(1, 99);
        System.out.println();
        System.out.println("Size - " + arr.size());
        System.out.println("Index 0 - " + arr.get(0));
        System.out.println("Index 1 - " + arr.get(1));
        System.out.println("Index 2 - " + arr.get(2));
        System.out.println("Index 3 - " + arr.get(3));
        System.out.println();
        System.out.println("Contains 20 - " + arr.contains(20));
        System.out.println("Contains 50 - " + arr.contains(50));

        int removed = arr.remove(1);
        System.out.println();
        System.out.println("Removed - " + removed);
        System.out.println("New size - " + arr.size());
        System.out.println("Index 1 after remove - " + arr.get(1));
    }
}