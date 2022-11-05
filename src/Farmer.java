import java.util.ArrayList;
import java.util.*;

public class Farmer {

    private String name;
    private double exp;
    private FarmerLevel Level;
    private double objectCoins;
    private Tile freeTile;

    private Tool wateringCan;
    private Tool plow;
    private Tool fertilizer;
    private Tool shovel;
    private Tool pickaxe;

    Scanner sc = new Scanner(System.in);

    public Farmer(String name, Tile freetile) {

        this.name = name;
        this.freeTile = freetile; 
        this.objectCoins = 100;
        this.exp = 0;
        this.Level = new FarmerLevel();

        // gives this.wateringCan appropriate attributes and functions
        initWateringCan();

        // gives this.plow appropriate attributes and functions
        initPlow();

        // gives this.fertilizer appropriate attributes and functions
        initFertilizer();

        // gives this.shovel appropriate attributes and functions
        initShovel();

        // gives this.pickaxe appropriate attributes and functions
        initPickaxe();
    }

    public ToolStatus usePlow(Farmer farmer) {
        return this.plow.use(farmer);
    }

    public ToolStatus useWateringCan(Farmer farmer) {
        return this.wateringCan.use(farmer);
    }

    public ToolStatus useFertilizer(Farmer farmer) {
        return this.fertilizer.use(farmer);
    }

    public ToolStatus useShovel(Farmer farmer) {
        return this.shovel.use(farmer);
    }

    public ToolStatus usePickaxe(Farmer farmer) {
        return this.pickaxe.use(farmer);
    }

    private void initWateringCan() {
        this.wateringCan = new Tool(0, 0.5) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                int waterBonusInc = farmer.Level.getWaterBonusInc();

                ToolStatus ts = new ToolStatus();

                int timesWatered = tile.getCrop().getTimesWatered();
                int waterBonus = tile.getCrop().getWaterBonus();
                ts.setFeedback("Failed to water crop");

                if (tile.getCrop() != null) {
                    if (timesWatered < waterBonus + waterBonusInc) {
                        tile.getCrop().water();
                        ts.setFeedback("Crop successfully watered");
                        ts.setSuccess(true);
                        if (timesWatered == waterBonus + waterBonusInc) {
                            ts.setFeedback("Crop's water bonus has been reached :D");
                        }

                        // give exp
                        farmer.giveExp(this.getExpGain());
                    }
                }

                return ts;
            }
        };
    }

    private void initPlow() {

        this.plow = new Tool(0, 0.5) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                ts.setFeedback("Tile cannot be plowed");

                if(!tile.isPlowed() && tile.isAvailable()){
                    tile.setPlowed(true);

                    // give exp to farmer
                    farmer.giveExp(this.getExpGain());


                    ts.setFeedback("Tile successfully plowed");
                    ts.setSuccess(true);
                }

                return ts;
            }
        };
    }

    private void initFertilizer() {

        this.fertilizer = new Tool(10.0, 4.0) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                int fertilizerBonus = tile.getCrop().getFertilizerBonus();
                int fertilizerBonusInc = farmer.Level.getFertilizerBonusInc();

                int timesFertilized = tile.getCrop().getTimesFertilized();
                ts.setFeedback("Failed to fertilize crop");

                // check if tile has a crop
                if (tile.getCrop() == null) {
                    ts.setFeedback("No crop to fertilize");
                    return ts;
                }

                if(tile.getCrop().isWithered()){
                    ts.setFeedback("Withered crop cannot be fertilized");
                    return ts;
                }

                // check if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("Not enough money to fertilize crop");
                    return ts;
                }

                // check if crop has not exceeded fertilizer bonus
                if (timesFertilized < fertilizerBonus + fertilizerBonusInc) {
                    tile.getCrop().fertilize();
                    ts.setFeedback("Crop successfully fertilized");

                    if (timesFertilized == fertilizerBonus + fertilizerBonusInc) {
                        ts.setFeedback("Crop's fertilizer bonus has been reached :D");
                    }

                    // give farmer exp
                    farmer.giveExp(this.getExpGain());

                    // take money from farmer
                    farmer.updateCoins(-1 * this.getCost());

                    ts.setSuccess(true);
                }

                return ts;
            }
        };
    }

    private void initPickaxe() {
        this.pickaxe = new Tool(50.0, 15.0) {

            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                if(tile.isAvailable()){
                    ts.setFeedback("Tile is empty");
                    return ts;
                }

                // check if tile has no rocks
                if(!tile.getTileStatus().getReason().contains("rocks") || 
                   !tile.getTileStatus().getReason().contains("Rocks")){
                    ts.setFeedback("No rocks to mine");
                    return ts;
                }

                // check if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("Not enough money to mine rocks");
                    return ts;
                }

                // update tilestatus
                tile.makeAvailable();
                ts.setFeedback("Successfully removed rocks from tile");

                // give exp
                farmer.giveExp(this.getExpGain());
                // take money
                farmer.updateCoins(-1 * this.getCost());

                ts.setSuccess(true);

                return ts;
            }
        };
    }


    private void initShovel(){

        this.shovel = new Tool(7.0, 2.0){

            @Override
            public ToolStatus use(Farmer farmer){
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();
                ts.setFeedback("Failed to dig up crop");
                // check if tile has a withered plant
                if (tile.getCrop() == null)
                    return ts;

                if (tile.isAvailable()){
                    ts.setFeedback("Tile is empty");
                }


                if(!tile.getCrop().isWithered()){
                    ts.setFeedback("Crop is not withered");
                    return ts;
                }

                if (farmer.getObjectCoins() < this.getCost()){
                    ts.setFeedback("Not enough money to dig up crop");
                    return ts;
                }

                tile.setCrop(null);
                tile.makeAvailable();

                farmer.giveExp(this.getExpGain());
                farmer.updateCoins(-1 * this.getCost());

                ts.setSuccess(true);


                return ts;
            }
        };
    }

    protected Tile getFreeTile() {
        return this.freeTile;
    }

    public String getName() {

        return this.name;
    }

    public double getObjectCoins() {

        return this.objectCoins;
    }
    

    public void buySeed(Seed seed) {
        this.objectCoins -= seed.getCost();
    }

    public void plantSeed(Seed seed) {

        buySeed(seed);
        this.freeTile.setCrop(new Crop(seed.getName()));
        System.out.println("\nYou have planted a " + seed.getName() + "!");

        this.freeTile.getTileStatus().hasCrop();
    }

    public boolean harvestCrop() {


        if(freeTile.getCrop() == null)
            return false;

        Crop crop = freeTile.getCrop();

        if(!crop.isHarvestable() && !crop.isWithered())
            return false;

        int produced = crop.getHarvestYield();

        System.out.println("\nYou have harvested " + produced + " " + crop.getName() + "!");

        double harvestTotal = produced * (crop.getBasePrice());
        double waterBonus = harvestTotal * 0.2 * (crop.getTimesWatered() - 1);
        double fertilizerBonus = harvestTotal * 0.5 * crop.getTimesFertilized();
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        // Add coins to wallet
        this.objectCoins += finalHarvestPrice;

        System.out.println("You have earned " + finalHarvestPrice + " coins!");

        // set tile to empty
        freeTile.setCrop(null);

        freeTile.makeAvailable();

        return true;
    }

    public FarmerLevel getFarmerLevel() {
        return this.Level;
    }

    public void giveExp(double expGain) {
        this.exp += expGain;
    }

    public void updateCoins(double amount) {
        this.objectCoins += amount;
    }

    public double getExp() {
        return this.exp;
    }
}
