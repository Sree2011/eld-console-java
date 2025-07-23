
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
        Scanner sc = new Scanner(System.in);
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
        return genlist;
    }

}
