public class WateringCan {
    private double cost;

    public WateringCan() {
        cost = 0;
    }

    public void waterCrop(Tile tile) {
        tile.getCrop().addWater();
    }
}
