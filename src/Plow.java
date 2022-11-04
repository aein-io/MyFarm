

public class Plow {
    private double cost;

    public Plow(double cost) {
        this.cost = 0;
    }

    public boolean usePlow(Tile tile) {
        
        // Check if existing plant
        // Check if plowed
        
        return tile.setPlowed();
    }
}
