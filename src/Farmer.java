import java.util.ArrayList;

public class Farmer {
    private String name;
    private double objectCoins;

    private ArrayList<Turnip> turnipSeeds;
    private Plow plow;
    private WateringCan wateringCan;
    private Fertilizer fertilizer;

    public Farmer() {
        this.name = "Jack";
        this.objectCoins = 100;

        this.turnipSeeds = new ArrayList<Turnip>();
        this.plow = new Plow();
        this.wateringCan = new WateringCan();
        this.fertilizer = new Fertilizer();
    }

    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;

        this.turnipSeeds = new ArrayList<Turnip>();
        this.plow = new Plow();
        this.wateringCan = new WateringCan();
        this.fertilizer = new Fertilizer();
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
        tile.setCrop(new Turnip());
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
        new Tile(); // Resets tile?
    }
}
