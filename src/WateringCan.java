

public class WateringCan {
    private double cost;

    public WateringCan(double cost) {
        this.cost = 0;
    }

    public boolean useWateringCan(Tile tile, Turnip crop) {
        
        // Not plowed
        if(!tile.setPlowed())
            return false;

        // Withered
        if(tile.setWithered())
            return false;
        
        return !tile.setPlowed();
    }
    
}
