package com.example.daa;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {
            100,
            1_000,
            10_000,
            100_000
    };

    private static final int UPDATE_OPERATIONS = 1000;
    private static final int RUNS = 5;
    private static final int GET_OPERATIONS = 10_000;
    private static final int SEARCH_OPERATIONS = 1000;
    private static PrintWriter csvWriter;

    public static void main(String[] args) throws FileNotFoundException {

        File resultsFolder = new File("results");

        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }

        csvWriter = new PrintWriter("results/results.csv");

        csvWriter.println(
                "workload,variant,structure,n,time_ms,steps,moves,comparisons"
        );

        for (int n : SIZES) {
            runW1(n);
            runW2(n);
            runW3(n);
            runW4(n);
        }

        csvWriter.close();

        System.out.println();
        System.out.println("Benchmark finished");
        System.out.println("results/results.csv created");
    }

    //W1
    private static void runW1(int n) {

        Random random = new Random(42);

        int[] values = new int[n];
        int[] indexes = new int[GET_OPERATIONS];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt();
        }

        for (int i = 0; i < GET_OPERATIONS; i++) {
            indexes[i] = random.nextInt(n);
        }

        runW1DA(n, values, indexes);
        runW1LinkedList(n, values, indexes);
    }



    //W2
    private static void runW2(int n) {

        Random random = new Random(42);

        int[] values = new int[n];
        int[] queries = new int[SEARCH_OPERATIONS];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt(1_000_000);
        }

        for (int i = 0; i < SEARCH_OPERATIONS / 2; i++) {
            int index = random.nextInt(n);
            queries[i] = values[index];
        }

        for (int i = SEARCH_OPERATIONS / 2; i < SEARCH_OPERATIONS; i++) {
            queries[i] = -1 - random.nextInt(1_000_000);
        }

        runW2DA(n, values, queries);
        runW2LinkedList(n, values, queries);
    }


    //W3
    private static void runW3(int n) {

        Random random = new Random(42);

        int[] values = new int[n];
        int[] newValues = new int[UPDATE_OPERATIONS];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt();
        }

        for (int i = 0; i < UPDATE_OPERATIONS; i++) {
            newValues[i] = random.nextInt();
        }

        runW3DA(n, values, newValues, true);
        runW3LinkedList(n, values, newValues, true);

        runW3DA(n, values, newValues, false);
        runW3LinkedList(n, values, newValues, false);
    }



    //W1DA
    private static void runW1DA(int n, int[] values, int[] indexes) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            DA array = new DA(metrics);

            for (int value : values) {
                array.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int index : indexes) {
                array.get(index);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W1",
                "-",
                "DynamicArray",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println();
        System.out.println(
                "------W1 DA------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
    }


    //W1LL
    private static void runW1LinkedList(int n, int[] values, int[] indexes) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            MyLinkedList list = new MyLinkedList(metrics);

            for (int value : values) {
                list.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int index : indexes) {
                list.get(index);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W1",
                "-",
                "MyLinkedList",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W1 MyLinkedList------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }

    //W2DA
    private static void runW2DA(int n, int[] values, int[] queries) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            DA array = new DA(metrics);

            for (int value : values) {
                array.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int query : queries) {
                array.contains(query);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W2",
                "-",
                "DynamicArray",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W2 DA------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
    }

    //W2LL
    private static void runW2LinkedList(int n, int[] values, int[] queries) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            MyLinkedList list = new MyLinkedList(metrics);

            for (int value : values) {
                list.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int query : queries) {
                list.contains(query);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W2",
                "-",
                "MyLinkedList",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W2 MyLinkedList------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }


    //W3DA
    private static void runW3DA(int n, int[] values, int[] newValues, boolean head) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        String variant = head ? "head" : "middle";

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            DA array = new DA(metrics);

            for (int value : values) {
                array.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int value : newValues) {
                int index = head ? 0 : n / 2;
                array.add(index, value);
            }

            for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                int index = head ? 0 : n / 2;
                array.remove(index);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W3",
                variant,
                "DynamicArray",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W3 DA " + variant + "------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
    }


    //W3LL
    private static void runW3LinkedList(int n, int[] values, int[] newValues, boolean head) {
        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        String variant = head ? "head" : "middle";

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            MyLinkedList list = new MyLinkedList(metrics);

            for (int value : values) {
                list.add(value);
            }

            metrics.reset();

            long start = System.nanoTime();

            for (int value : newValues) {
                int index = head ? 0 : n / 2;
                list.add(index, value);
            }

            for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                int index = head ? 0 : n / 2;
                list.remove(index);
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        double medianTime = median(times);
        saveResult(
                "W3",
                variant,
                "MyLinkedList",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W3 MyLinkedList " + variant + "------" +
                        "\n n = " + n + "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons
        );

        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }



    //W4
    private static void runW4(int n) {

        Random random = new Random(42);

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt();
        }

        double[] times = new double[RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        boolean sorted = true;

        for (int run = -1; run < RUNS; run++) {

            Metrics metrics = new Metrics();
            MinHeap heap = new MinHeap(metrics);

            long start = System.nanoTime();

            for (int value : values) {
                heap.insert(value);
            }

            boolean currentRunSorted = true;
            int previous = Integer.MIN_VALUE;

            while (heap.size() > 0) {
                int current = heap.extractMin();

                if (current < previous) {
                    currentRunSorted = false;
                }

                previous = current;
            }

            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            if (run >= 0) {
                times[run] = timeMs;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();

                if (!currentRunSorted) {
                    sorted = false;
                }
            }
        }

        double medianTime = median(times);
        saveResult(
                "W4",
                "-",
                "MinHeap",
                n,
                medianTime,
                steps,
                moves,
                comparisons
        );

        System.out.println(
                "------W4 MinHeap------" +
                        "\n n = " + n +  "   |   Median time = " + medianTime + " ms" +
                        "\n Steps = " + steps + "   |   Moves = " + moves +
                        "\n Comparisons = " + comparisons + "   |   Non-decreasing = " + sorted
        );

        System.out.println();
        System.out.println("===============================");
        System.out.println();
    }


    private static void saveResult(
            String workload,
            String variant,
            String structure,
            int n,
            double timeMs,
            long steps,
            long moves,
            long comparisons
    ) {
        csvWriter.println(
                workload + "," +
                        variant + "," +
                        structure + "," +
                        n + "," +
                        timeMs + "," +
                        steps + "," +
                        moves + "," +
                        comparisons
        );
    }



    //mediana
    private static double median(double[] times) {

        for (int i = 0; i < times.length - 1; i++) {
            for (int j = 0; j < times.length - 1 - i; j++) {

                if (times[j] > times[j + 1]) {
                    double temp = times[j];
                    times[j] = times[j + 1];
                    times[j + 1] = temp;
                }
            }
        }

        return times[times.length / 2];
    }

}