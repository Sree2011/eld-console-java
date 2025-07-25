public class Main{
    public static void main(String[] args) {
    Generator[] generators = new Generator[]{
    new Generator(1, 125, 5000, 0.02f, 45f, 0.0003f),
    new Generator(2, 7, 300, 0.03f, 50f, 0.0035f),
    new Generator(3, 9, 358, 0.025f, 52f, 0.0030f),
    new Generator(4, 7, 286, 0.03f, 48f, 0.0029f),
    new Generator(5, 6, 260, 0.035f, 55f, 0.0054f),
    new Generator(6, 8, 348, 0.02f, 50f, 0.0022f),
    new Generator(7, 6, 264, 0.027f, 51f, 0.0032f),
    new Generator(8, 6, 243, 0.03f, 53f, 0.0028f),
    new Generator(9, 8, 345, 0.025f, 47f, 0.0030f),
    new Generator(10, 10, 420, 0.015f, 40f, 0.0012f)
};

    float totalDemand = 2000f;
        ELDCalculator eldCalculator = new ELDCalculator(generators.length, generators, totalDemand);
        
        float[] ELDispatch = eldCalculator.lambdaIteration();
        for(int i = 0; i < ELDispatch.length; i++) {
            System.out.printf("Generator %d dispatched power: %.3f kW%n", i + 1, ELDispatch[i]);
        }
    }
}