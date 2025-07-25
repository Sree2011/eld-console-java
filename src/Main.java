/**
 * @brief Main class to run the Economic Load Dispatch (ELD) calculator.
 * This class initializes the generator data and total demand,
 * then invokes the ELDCalculator to compute the optimal power distribution.
 */
public class Main{

    /**
     * Main method to execute the ELD calculation.
     * It loads generator data from a CSV file or user input,
     * then performs the ELD calculation.
     * @param args command line arguments (not used)
     * 
     */
    public static void main(String[] args) {
        Generator[] genlist = InputLoader.loadFromCSV("input/10-generator_system.csv").toArray(new Generator[0]);


        // ArrayList<Generator> genlist = InputLoader.loadFromUser();
        if (genlist.length == 0) {
            System.out.println("No valid generators loaded. Exiting.");
            return;
        }

       // Generator[] genlist = genlist.toArray(new Generator[0]);
        System.out.println("Total generators loaded: " + genlist.length);
        System.out.printf("%-12s %-15s %-15s %-12s %-12s %-12s%n", 
                  "Gen ID", "Min Capacity", "Max Capacity", "a", "b", "c");
        System.out.println("--------------------------------------------------------------------------");
        for (Generator gen : genlist) {
            System.out.printf("%-12d %-15d %-15d %-12.3f %-12.3f %-12.3f%n",
                            gen.getGen_id(), gen.getMin_capacity(), gen.getMax_capacity(),
                            gen.getA(), gen.getB(), gen.getC());
        }
        float totalDemand = 2000f;
        ELDCalculator eldCalculator = new ELDCalculator(genlist.length, genlist, totalDemand);
        
        float[] ELDispatch = eldCalculator.lambdaIteration();
        for(int i = 0; i < ELDispatch.length; i++) {
            System.out.printf("Generator %d dispatched power: %.3f kW%n", i + 1, ELDispatch[i]);
        }
    }
}