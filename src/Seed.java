/**
 * Description of class
 *
 * @author
 */
public class Seed {
    private String name;
    private double cost;

    /**
     *
     * @param name
     */
    public Seed(String name) {
        this.name = name;
        // Don't we call an array here
    }

    /**
     *
     * @return
     */
    public String getSeedName() {

        return this.name;
    }

    /**
     *
     * @return
     */
    public double getSeedCost() {
        // No computation for now
        return this.cost;
    }
}
