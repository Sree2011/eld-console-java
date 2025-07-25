/**
 * Calculator class for performing Lambda Iteration-based Economic Load Dispatch.
 * 
 * @reference H. Saadat, *Power System Analysis*, 3rd ed. Maple Valley, WA: PSA Publishing, 2010.
 * Used to guide the cost function model and iterative convergence logic.

 */
public class ELDCalculator {
    private float lambda;
    private Generator[] genArray;
    private int numGenerators;
    private float totDemand;
    private float tolerance;
    private final int maxIterations;

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
        float[] P = new float[numGenerators];
        int iteration = 0;

        while (iteration < maxIterations) {
            float totalPower = 0f;

            for (int i = 0; i < numGenerators; i++) {
                float a = genArray[i].getA();
                float b = genArray[i].getB();
                float c = genArray[i].getC();

                P[i] = (lambda - b) / (2 * c);
                P[i] = genArray[i].validatePower(P[i]);  // Clamp within min/max limits
                totalPower += P[i];
            }

            float mismatch = totDemand - totalPower;

            if (Math.abs(mismatch) <= tolerance) {
                System.out.println("✅ Converged Economic Load Dispatch values:");
                for (int i = 0; i < P.length; i++) {
                    System.out.printf("Generator %d : %.3f kW%n", i + 1, P[i]);
                }
                break;
            }

            // Adaptive lambda step based on mismatch
            float stepSize = mismatch * 0.0001f;
            lambda += stepSize;

            // Log progress every 100 iterations
            if (iteration % 100 == 0) {
                System.out.printf("🔄 Iteration %d | λ = %.4f | Total Gen = %.2f | Mismatch = %.4f%n",
                                  iteration, lambda, totalPower, mismatch);
            }

            iteration++;
        }

        if (iteration == maxIterations) {
            float finalMismatch = totDemand - sum(P);
            System.out.printf("⚠️ Max iterations reached.\nFinal λ: %.4f | Total Gen: %.2f | Demand: %.2f | Diff: %.4f%n",
                              lambda, sum(P), totDemand, finalMismatch);
        }

        return P;
    }

    // Helper method to sum array values
    private float sum(float[] array) {
        float total = 0f;
        for (float value : array) {
            total += value;
        }
        return total;
    }
}