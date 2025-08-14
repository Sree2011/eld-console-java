package com.nandini.eld;
import java.util.logging.*;
/**
 * Calculator class for performing Lambda Iteration-based Economic Load Dispatch.
 *
 * This class computes the optimal power distribution among generators
 * to minimize the total cost while meeting a specified power demand.
 *
 * @author Sree Sai Nandini
 * @version 1.0
 * @see Generator
 * @see InputLoader
 * @see Main
 */

public class ELDCalculator {
    private float lambda;
    private final Generator[] genArray;
    private final int numGenerators;
    private final float totDemand;
    private final float tolerance;
    private final int maxIterations;
    private static final Logger LOGGER = Logger.getLogger(ELDCalculator.class.getName());


    /**
     * Constructor to initialize ELDCalculator with generator data and demand.
     *
     * @param numGenerators number of generators
     * @param genArray array of Generator objects
     * @param totDemand total power demand
     */
    public ELDCalculator(int numGenerators, Generator[] genArray, float totDemand) {
        this.lambda = 1f;
        this.genArray = genArray;
        this.numGenerators = numGenerators;
        this.totDemand = totDemand;
        this.tolerance = 0.0001f;
        this.maxIterations = 10000;
    }

    /**
     * Performs Lambda Iteration to compute optimal power distribution.
     *
     * @return array of dispatched power values for each generator
     */
    public float[] lambdaIteration() {
        float[] powerArray = new float[numGenerators];
        int iteration = 0;

        while (iteration < maxIterations) {
            float totalPower = 0f;

            for (int i = 0; i < numGenerators; i++) {
                float a = genArray[i].getA();
                float b = genArray[i].getB();
                float c = genArray[i].getC();

                powerArray[i] = (lambda - b) / (2 * c);
                powerArray[i] = genArray[i].validatePower(powerArray[i]);  // Clamp within min/max limits
                totalPower += powerArray[i];
            }

            float mismatch = totDemand - totalPower;

            if (Math.abs(mismatch) <= tolerance  && LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO,"✅ Converged Economic Load Dispatch values:");
                for (int i = 0; i < powerArray.length; i++) {
                    LOGGER.log(Level.INFO,String.format("Generator %d : %.3f kW%n", i + 1, powerArray[i]));
                }
                break;
            }

            // Adaptive lambda step based on mismatch
            float stepSize = mismatch * 0.0001f;
            lambda += stepSize;

            // Log progress every 100 iterations
            if (iteration % 100 == 0 && LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, String.format("Iteration %d | lambda = %.4f | Total Gen = %.2f | Mismatch = %.4f%n",
                                  iteration, lambda, totalPower, mismatch));
            }

            iteration++;
        }

        if (iteration == maxIterations  && LOGGER.isLoggable(Level.WARNING)) {
            float finalMismatch = totDemand - sum(powerArray);
            LOGGER.log(Level.WARNING,String.format("⚠️ Max iterations reached.%nFinal lambda: %.4f | Total Gen: %.2f | Demand: %.2f | Diff: %.4f%n",
                              lambda, sum(powerArray), totDemand, finalMismatch));
        }

        return powerArray;
    }

    /**
     * Helper method to sum the values of an array.
     * @param array the array to sum
     * @return total sum of the array values
     */
    // Helper method to sum array values
    private float sum(float[] array) {
        float total = 0f;
        for (float value : array) {
            total += value;
        }
        return total;
    }
}