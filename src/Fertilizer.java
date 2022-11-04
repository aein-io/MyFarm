public class Fertilizer {
    private double cost;

    public Fertilizer() {
        cost = 10;
    }

    public void fertilizeCrop(Tile tile) {
        tile.getCrop().addFertilizer();
    }
}
