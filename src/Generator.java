/**
 * @brief Stores details about each generator and calculates its cost for a given output.
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *   <li><code>gen_id</code> — Unique identifier for the generator</li>
 *   <li><code>min_capacity</code> — Minimum generation capacity (in MW)</li>
 *   <li><code>max_capacity</code> — Maximum generation capacity (in MW)</li>
 *   <li><code>a</code>, <code>b</code>, <code>c</code> — Cost function coefficients</li>
 *   <li><code>current_power</code> — Current power output</li>
 * </ul>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li><code>Generator(...)</code> — Constructor to initialize generator parameters</li>
 *   <li><code>calculateCost(float power)</code> — Calculates cost for given power</li>
 *   <li><code>isWithinLimits(float power)</code> — Checks if power is within capacity bounds</li>
 * </ul>
 *
 * @author Sree Sai Nandini
 * @version 1.0
 * @see ELDCalculator
 * @see InputLoader
 * @see Main
 */

public class Generator{
    int gen_id;
    int min_capacity;
    int max_capacity;
    float a,b,c;


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
        float C_p = (a*(power * power)+(b*power)+c);
        return C_p;
    }

    /**
     * Checks if the given power is within the limits of the generator power capacity
     * @param power the given power
     * @return true if given power is within the limits, else false
     */
    public float validatePower(float power) {
        if (power < min_capacity) {
            return min_capacity;
        } else if (power > max_capacity) {
            return max_capacity;
        } else {
            return power;
        }
    }



    /**
     * Getter method for generator id
     * @return gen_id Generator id
     */
    public int getGen_id() {
        return gen_id;
    }

    /**
     * Setter method for generator id
     * @param gen_id Generator id
     */
    public void setGen_id(int gen_id) {
        this.gen_id = gen_id;
    }

    /**
     * Getter method for minimum generator capacity
     * @return min_capacity Minimum generator capacity
     */
    public int getMin_capacity() {
        return min_capacity;
    }

    /**
     * Setter method for minimum generator capacity
     * @param min_capacity Minimum generator capacity
     */
    public void setMin_capacity(int min_capacity) {
        this.min_capacity = min_capacity;
    }

    /**
     * Getter method for maximum generator capacity
     * @return max_capacity Maximum generator capacity
     */
    public int getMax_capacity() {
        return max_capacity;
    }

    /**
     * Setter method for maximum generator capacity
     * @param max_capacity Maximum generator capacity
     */
    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    /**
     * Returns the quadratic coefficient 'a' of the generator's cost function.
     * This term represents the fixed cost component.
     *
     * @return the 'a' coefficient
     */
    public float getA() {
        return a;
    }

    /**
     * Sets the quadratic coefficient 'a' of the generator's cost function.
     * Use this to update the fixed cost component.
     *
     * @param a the new value for the 'a' coefficient
     */
    public void setA(float a) {
        this.a = a;
    }

    /**
     * Returns the linear coefficient 'b' of the generator's cost function.
     * This term scales linearly with power output.
     *
     * @return the 'b' coefficient
     */
    public float getB() {
        return b;
    }

    /**
     * Sets the linear coefficient 'b' of the generator's cost function.
     * This updates how cost changes linearly with generation.
     *
     * @param b the new value for the 'b' coefficient
     */
    public void setB(float b) {
        this.b = b;
    }

    /**
     * Returns the quadratic coefficient 'c' of the generator's cost function.
     * This term controls how cost curves with power output.
     *
     * @return the 'c' coefficient
     */
    public float getC() {
        return c;
    }

    /**
     * Sets the quadratic coefficient 'c' of the generator's cost function.
     * Use this to define the rate of cost curvature.
     *
     * @param c the new value for the 'c' coefficient
     */
    public void setC(float c) {
        this.c = c;
    }
}