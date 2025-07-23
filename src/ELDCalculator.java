import Generator;

/**
 * Calculator class for performing Lambda Iteration-based Economic Load Dispatch.
 */
public class ELDCalculator {
    private float lambda;
    private Generator[] genArray;
    private int numGenerators;
    private float totDemand;
    private float tolerance;
    private int maxIterations;

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
        this.tolerance = 0.001f;
        this.maxIterations = 100;
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
                P[i] = genArray[i].validatePower(P[i]);
                totalPower += P[i];
            }

            if (Math.abs(totalPower - totDemand) <= tolerance) {
                System.out.println("✅ Converged Economic Load Dispatch values:");
                for (int i = 0; i < P.length; i++) {
                    System.out.printf("Generator %d : %.3f kW%n", i + 1, P[i]);
                }
                break;
            } else if (totalPower < totDemand) {
                lambda += 0.01f;
            } else {
                lambda -= 0.01f;
            }

            iteration++;
        }

        if (iteration == maxIterations) {
            System.out.println("⚠️ Max iterations reached. Solution may not be optimal.");
        }

        return P;
    }
}