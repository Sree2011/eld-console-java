public class Generator{
    int gen_id;
    int min_capacity;
    int max_capacity;
    float a,b,c;
    float current_power;

    public Generator(int gen_id, int min_capacity, int max_capacity, float a, float b, float c, float current_power) {
        this.gen_id = gen_id;
        this.min_capacity = min_capacity;
        this.max_capacity = max_capacity;
        this.a = a;
        this.b = b;
        this.c = c;
        this.current_power = current_power;
    }

    public static float calculateCost(float power){
        float C_p = (a*(power^2)+(b*p)+c);
        return C_p;
    }

    public static boolean isWithinLimits(power){
        if (min_capacity <= power && power <= max_capacity){
            return true;
        }
        return false;
    }
}