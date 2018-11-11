package nl.bongers.testdome;

import java.util.function.Function;

public class Train {

    //private Hashtable<Integer, Integer> wagons;
    private int[] wagons; //Van hashtable naar int[] niet veel sneller.

    public Train(int wagonCount, Function<Integer, Integer> fillWagon) {
        //wagons = new Hashtable<>(wagonCount);
        wagons = new int[wagonCount];

        for (int i = 0; i < wagonCount; i++) {
            wagons[i] = fillWagon.apply(i);
            //wagons.put(i, fillWagon.apply(i));
        }
    }

    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        final int wagonCount = 100_000_000; //Dit is alleen acceptabel met int array. Niet met hashtable
        final Train train = new Train(wagonCount, x -> x);
        for (int i = 0; i < wagonCount; i++) {
            System.out.println("Wagon: " + i + ", cargo: " + train.peekWagon(i)); //Log weglaten scheelt uiteraard veel tijd, maar lijkt me niet gewenst
        }
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }

    public int peekWagon(int wagonIndex) {
        //return wagons.get(wagonIndex);
        return wagons[wagonIndex];
    }
}