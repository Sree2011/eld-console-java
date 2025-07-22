import java.util.*;
public class InputLoader{
    public static void loadFromUser(){
        ArrayList<Generator> genlist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total power demand:");
        int total_demand = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter total no.of generators: ");
        int no_of_generators = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<no_of_generators;i++){
            System.out.println("Enter the cost coefficients (a,b,c) of the generators one by one in new lines: ");
            float a = sc.nextFloat();
            sc.nextLine();
            float b = sc.nextFloat();
            sc.nextLine();
            float c = sc.nextFloat();
            sc.nextLine();
            System.out.println("Enter the minimum power capacity: ");
            int min_capacity = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the maximum power capacity: ");
            int max_capacity = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the current power of the generator: ");
            float current_power = sc.nextFloat();
            sc.nextLine();

            Generator gobj = new Generator(i, min_capacity, max_capacity, a, b, c, current_power);
            genlist.put(gobj);
        }
    }

}