public class WateringCan {
    private double cost;

    public WateringCan() {
        this.cost = 0;
    }

    public double getCost() {
        return this.cost;
    }

    public void waterCrop(Tile tile) {
        tile.getCrop().addWater();
    }
}
