public class Plow {
    private double cost;

    public Plow() {
        this.cost = 0;
    }

    public double getCost() {
        return cost;
    }

    public void plowTile(Tile tile) {
        tile.setPlowed();
    }
}
