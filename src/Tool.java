public class Tool {
    private double cost;

    public Tool(double cost) {
        this.cost = 0;
    }

    // Plow Tile
    public boolean use(Tile tile) {
        
        if(!tile.plowed())
            return tile.plowed();
        
        return !tile.plowed();
    }
}
