import java.util.ArrayList;

public class Farmer {

    private String name;
    private double objectCoins;

    private ArrayList<Turnip> seedArray;
    private WateringCan wateringCan;
    private Plow plow;
    private Fertilizer fertilizer;

    public Farmer() {
        this.name = "Jack";
        this.objectCoins = 100;

        this.seedArray = new ArrayList<Turnip>();
        this.wateringCan = new WateringCan();
        this.plow = new Plow();
        this.fertilizer = new Fertilizer();
    }

    public Farmer(String name) {

        this.name = name;
        this.objectCoins = 100;

        this.seedArray = new ArrayList<Turnip>();
        this.wateringCan = new WateringCan();
        this.plow = new Plow();
        this.fertilizer = new Fertilizer();
    }

    public String getName() {

        return this.name;
    }

    public double getObjectCoins() {

        return this.objectCoins;
    }

    public ArrayList<Turnip> getSeedArray() {

        return seedArray;
    }

    public void buySeed(Turnip seed) {

        this.seedArray.add(seed);
        this.objectCoins -= seed.getCost();
    }

    public void plantSeed(Turnip seed, Tile tile) {

        tile.setCrop(new Turnip());
    }

    public void harvestCrop(Tile tile) {

        int produced = tile.getCrop().getProduce();
        double harvestTotal = produced * (tile.getCrop().getBasePrice());

        double waterBonus = 0;
        double fertilizerBonus = 0;

        // Check if water needs are met
        // Check if fertilizer needs are met

        // Add coins to wallet
        this.objectCoins += harvestTotal;

        // Make a new tile class (to set it back to null)
        new Tile();

        // Successfully harvested crop
    }

    public void useWateringCan(Tile tile) {

    }

    public void useFertilizer(Tile tile) {

    }

    public void usePlow(Tile tile) {
        if(!tile.isPlowed())
            plow.plowTile(tile);
    }
}

