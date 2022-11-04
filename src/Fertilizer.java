public class Fertilizer {
    private double cost;

    public Fertilizer() {
        this.cost = 10;
    }

    public double getCost() {
        return this.cost;
    }

    public void fertilizeCrop(Tile tile) {
        tile.getCrop().addFertilizer();
    }
}
