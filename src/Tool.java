/**
 * This class is used to create a generic tool object
 * @version 1.0
 */
public class Tool {
    private double cost;
    private double expGain;

    /**
     * This is the constructor for the Tool class
     * @param cost The cost of the tool
     * @param expGain The experience gained from using the tool
     */
    public Tool(double cost, double expGain) {
        this.cost = cost;
        this.expGain = expGain;
    }

    /**
     * Gets the amount of experience gained from using the tool
     * @return expGain
     */
    public double getExpGain() {
        return this.expGain;
    }

    /**
     * Gets the cost of the tool
     * @return cost
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Method to use the tool
     * @param farmer The farmer using the tool
     * @returns a new ToolStatus object
     */
    public ToolStatus use(Farmer farmer) {
        // utility class meant to be overriden
        return new ToolStatus();
    }
}
