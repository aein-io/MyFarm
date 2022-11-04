public class Farmer {
    private String name;
    private double objectCoins;

    private Turnip crop;
    private Plow plow;
    private WateringCan wateringCan;
    private Fertilizer fertilizer;

    public Farmer() {
        this.name = "Jack";
        this.objectCoins = 100;
    }

    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;
    }

    public String getName() {
        return this.name;
    }

    public double getObjectCoins() {
        return this.objectCoins;
    }

    public void plow(Tile tile) {
        if (!tile.setPlowed()) {
            plow.usePlow(tile);
        }
    }
    
    public void plant(Tile tile) {        
        tile.setCrop(crop);
    }
    
    public void buy(Turnip crop) {
        this.objectCoins = this.objectCoins - crop.getCost();
    }
    
    public void water(Turnip crop) {

    }

    public void fertilize(Turnip crop) {

    }

    public void harvest(Tile tile) {
        // Harvest crop
        // Reset tile
    }
}
