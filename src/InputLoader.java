
import java.io.File;
import java.util.*;

/**
 * Utility class for handling user input related to generator parameters.
 * <p>Prompts the user to enter total load demand, number of generators,
 * and each generator's cost coefficients and capacity limits.
 *
 * <p>Creates and returns a list of Generator objects for further dispatch logic.
 *
 * @author Sree Sai Nandini
 * @version 1.0
 * @see Generator
 * @see ELDCalculator
 * @see Main
 */
public class InputLoader{

    /**
     * Accepts the no.of generators and generator parameters for each one as inputs from the user ,
     * creates generator object for each generator, adds all of them into arraylist and returns the arraylist.
     *
     * @return genlist the arraylist of generator objects
     */
    public static ArrayList<Generator> loadFromUser(){
        ArrayList<Generator> genlist = new ArrayList<>();
        try(Scanner sc = new Scanner(System.in);){
            System.out.println("Enter total power demand:");
            int total_demand = sc.nextInt();
            System.out.println("Enter total no.of generators: ");
            int no_of_generators = sc.nextInt();
            for(int i=0;i<no_of_generators;i++){
                System.out.println("Enter the cost coefficients (a,b,c) of the generators one by one in new lines: ");
                float a = sc.nextFloat();
                float b = sc.nextFloat();
                float c = sc.nextFloat();
                System.out.println("Enter the minimum power capacity: ");
                int min_capacity = sc.nextInt();
                System.out.println("Enter the maximum power capacity: ");
                int max_capacity = sc.nextInt();

                Generator gobj = new Generator(i, min_capacity, max_capacity, a, b, c);
                genlist.add(gobj);
            }
        }
            catch(Exception e){
                System.out.println("Invalid input! Please enter valid numbers.");
                return new ArrayList<>(); // Return empty list on error}
            }
        return genlist;
    }

    /**
     * Loads generator data from a CSV file.
     * Each line in the file should contain generator parameters in the format:
     * gen_id, min_capacity, max_capacity, a, b, c
     *
     * @param filePath path to the CSV file
     * @return ArrayList of Generator objects
     */
    public static ArrayList<Generator> loadFromCSV(String filePath) {
        ArrayList<Generator> genlist = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath))) {
            // Skip header if present
            if (sc.hasNextLine()) sc.nextLine(); 

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 6) continue; // Skip invalid lines

                int gen_id = Integer.parseInt(parts[0].trim());
                int min_capacity = Integer.parseInt(parts[1].trim());
                int max_capacity = Integer.parseInt(parts[2].trim());
                float a = Float.parseFloat(parts[3].trim());
                float b = Float.parseFloat(parts[4].trim());
                float c = Float.parseFloat(parts[5].trim());

                Generator gobj = new Generator(gen_id, min_capacity, max_capacity, a, b, c);
                genlist.add(gobj);
            }
        } catch (Exception e) {
            System.out.println("Error reading from CSV: " + e.getMessage());
            return new ArrayList<>(); // Corrected: return empty list, not float[]
        }
        return genlist;
    }

    }

