/**
 * Stores details about each generator and calculates its cost
 * for a given output.
 *
 * Fields:
 * <hr>
 *  <ul>
 *      <li>gen_id (int)- The generator id(a unique identifier)</li>
 *      <li>min_capacity (int) - The minimum power capacity of the generator</li>
 *      <li>max_capacity (int) - The maximum power capacity of the generator</li>
 *      <li>a,b,c (float) - the cost coefficients of the generator</li>
 *      <li>current_power (float) - The current power of the generator</li>
 * </ul>
 *
 * Methods:
 *
 * <ul>
 *  <li>public Generator(int gen_id, int min_capacity, int max_capacity, float a, float b, float c, float current_power) - the parameterised constructor</li>
 *  <li>public static float calculateCost(float power) - Calculates cost for the given power using cost coefficients</li>
 *  <li>public static boolean isWithinLimits(power) - Checks if the given power is within the generator's minimum and maximum capacity limits</li>
 * </ul>
 */
public class Generator{
    int gen_id;
    int min_capacity;
    int max_capacity;
    float a,b,c;
    float current_power;


    /**
     * The parameterised constructor for Generator class. It initiates an object with required parameters.
     * @param gen_id (int)- The generator id(a unique identifier)
     * @param min_capacity (int) - The minimum power capacity of the generator
     * @param max_capacity (int) - The maximum power capacity of the generator
     * @param a(float) - the cost coefficient(A) of the generator
     * @param b(float) - the cost coefficient(B) of the generator
     * @param c(float) - the cost coefficient(C) of the generator
     *
     */
    public Generator(int gen_id, int min_capacity, int max_capacity, float a, float b, float c) {
        this.gen_id = gen_id;
        this.min_capacity = min_capacity;
        this.max_capacity = max_capacity;
        this.a = a;
        this.b = b;
        this.c = c;

    }


    /**
     * Calculates the cost for given power based on the cost function of the generator
     * @param power The power for which cost needs to be calculated
     * @return C_p the cost for the given power
     */
    public float calculateCost(float power){
        float C_p = (a*(power * power)+(b*p)+c);
        return C_p;
    }

    /**
     * Checks if the given power is within the limits of the generator power capacity
     * @param power the given power
     * @return true if given power is within the limits, else false
     */
    public boolean isWithinLimits(float power){
        if (min_capacity <= power && power <= max_capacity){
            return true;
        }
        return false;
    }
}