package com.nandini.eld;
import java.util.*;

/**
 * Main class to run the Economic Load Dispatch (ELD) calculator.
 * 
 * This class initializes the generator data and total demand,
 * then invokes the ELDCalculator to compute the optimal power distribution.
 * 
 * @author Sree Sai Nandini
 * @version 1.0
 * @see Generator
 * @see InputLoader
 * @see ELDCalculator
 */
public class Main {

    public static void main(String[] args) {
        boolean useCSV = true; // Toggle this to true to load from CSV

        ArrayList<Generator> genlist = useCSV ? new ArrayList<>(InputLoader.loadFromCSV("input/10-generator_system.csv")) : InputLoader.loadFromUser();

        if (genlist.isEmpty()) {
            System.out.println("No valid generators loaded. Exiting.");
            return;
        }

        System.out.println("Total generators loaded: " + genlist.size());

        // Print generator details
        System.out.println("Generator Details:");
        System.out.printf("%-12s %-15s %-15s %-12s %-12s %-12s%n", 
                  "Gen ID", "Min Capacity", "Max Capacity", "a", "b", "c");
        System.out.println("--------------------------------------------------------------------------");
        for (Generator gen : genlist) {
            System.out.printf("%-12d %-15d %-15d %-12.3f %-12.3f %-12.3f%n",
                            gen.getGenId(), gen.getMinCapacity(), gen.getMaxCapacity(),
                            gen.getA(), gen.getB(), gen.getC());
        }

        float totalDemand = 2000f;
        ELDCalculator eldCalculator = new ELDCalculator(
            genlist.size(), genlist.toArray(new Generator[0]), totalDemand
        );

        float[] ELDispatch = eldCalculator.lambdaIteration();
        for (int i = 0; i < ELDispatch.length; i++) {
            System.out.printf("Generator %d dispatched power: %.3f kW%n", i + 1, ELDispatch[i]);
        }
    }
}
