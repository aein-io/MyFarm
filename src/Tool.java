public class Tool {
    private double cost;
    private double expGain;

    public Tool(double cost, double expGain) {
        this.cost = cost;
        this.expGain = expGain;
    }

    public double getExpGain() {
        return this.expGain;
    }

    public double getCost() {
        return this.cost;
    }

    public ToolStatus use(Farmer farmer) {
        // utility class meant to be overriden
        return new ToolStatus();
    }
}
