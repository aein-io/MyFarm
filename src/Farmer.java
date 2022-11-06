import java.util.ArrayList;
import java.util.*;

/**
 * This class represents the farmer in the game
 * @version 1.0
 */
public class Farmer {

    private String name;
    private double exp;
    private FarmerLevel level;
    private double objectCoins;
    private Tile freeTile;

    private Tool plow;
    private Tool wateringCan;
    private Tool fertilizer;
    private Tool pickaxe;
    private Tool shovel;

    Scanner sc = new Scanner(System.in);

    /**
     * Class constructor that initiates the farmer with default values and tools
     * @param name the name of the farmer
     * @param freetile the next available tile that the farmer can interact with
     */
    public Farmer(String name, Tile freetile) {

        this.name = name;
        this.freeTile = freetile; 
        this.objectCoins = 100;
        this.exp = 0;
        this.level = new FarmerLevel();

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

    /**
     * Method that uses the plow tool
     * @param farmer the player of the game
     * @returns a call to the plow.use method containing use success and context
     */
    public ToolStatus usePlow(Farmer farmer) {
        return this.plow.use(farmer);
    }

    /**
     * Method that uses the watering can tool
     * @param farmer the player of the game
     * @returns a call to the wateringCan.use method containing use success and context
     */
    public ToolStatus useWateringCan(Farmer farmer) {
        return this.wateringCan.use(farmer);
    }

    /**
     * Method that uses the fertilizer tool
     * @param farmer the player of the game
     * @returns a call to the fertilizer.use method containing use success and context
     */
    public ToolStatus useFertilizer(Farmer farmer) {
        return this.fertilizer.use(farmer);
    }

    /**
     * Method that uses the pickaxe tool
     * @param farmer the player of the game
     * @returns a call to the pickaxe.use method containing use success and context
     */
    public ToolStatus usePickaxe(Farmer farmer) {
        return this.pickaxe.use(farmer);
    }
    
    /**
     * Method that uses the shovel tool
     * @param farmer the player of the game
     * @returns a call to the shovel.use method containing use success and context
     */
    public ToolStatus useShovel(Farmer farmer) {
        return this.shovel.use(farmer);
    }

    /**
     * Method that initializes the plow tool
     */
    private void initPlow() {

        this.plow = new Tool(0, 0.5) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                ts.setFeedback("\nTile cannot be plowed.");

                if(!tile.isPlowed() && tile.isAvailable()){
                    tile.setPlowed(true);

                    // give exp to farmer
                    farmer.giveExp(this.getExpGain());


                    ts.setFeedback("\nTile successfully plowed.");
                    ts.setSuccess(true);
                }

                return ts;
            }
        };
    }

    /**
     * Method that initializes the watering can tool
     */
    private void initWateringCan() {
        this.wateringCan = new Tool(0, 0.5) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                int waterBonusInc = farmer.level.getWaterBonusInc();

                ToolStatus ts = new ToolStatus();

                int timesWatered = tile.getCrop().getTimesWatered();
                int waterBonus = tile.getCrop().getWaterBonus();
                ts.setFeedback("\nFailed to water crop.");

                if (tile.getCrop() != null) {
                    if (timesWatered < waterBonus + waterBonusInc) {
                        tile.getCrop().water();
                        ts.setFeedback("\nCrop successfully watered.");
                        ts.setSuccess(true);
                        if (timesWatered == waterBonus + waterBonusInc) {
                            ts.setFeedback("\nCrop's water bonus has been reached.");
                        }

                        // give exp
                        farmer.giveExp(this.getExpGain());
                    }
                }

                return ts;
            }
        };
    }

    /**
     * Method that initialized the fertilizer tool
     */
    private void initFertilizer() {

        this.fertilizer = new Tool(10.0, 4.0) {
            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                int fertilizerBonus = tile.getCrop().getFertilizerBonus();
                int fertilizerBonusInc = farmer.level.getFertilizerBonusInc();

                int timesFertilized = tile.getCrop().getTimesFertilized();
                ts.setFeedback("\nFailed to fertilize crop.");

                // check if tile has a crop
                if (tile.getCrop() == null) {
                    ts.setFeedback("\nNo crop to fertilize.");
                    return ts;
                }

                if(tile.getCrop().isWithered()){
                    ts.setFeedback("\nWithered crop cannot be fertilized.");
                    return ts;
                }

                // check if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("\nNot enough money to fertilize crop.");
                    return ts;
                }

                // check if crop has not exceeded fertilizer bonus
                if (timesFertilized < fertilizerBonus + fertilizerBonusInc) {
                    tile.getCrop().fertilize();
                    ts.setFeedback("\nCrop successfully fertilized.");

                    if (timesFertilized == fertilizerBonus + fertilizerBonusInc) {
                        ts.setFeedback("\nCrop's fertilizer bonus has been reached.");
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

    /**
     * Method that initializes the pickaxe tool
     */
    private void initPickaxe() {
        this.pickaxe = new Tool(50.0, 15.0) {

            @Override
            public ToolStatus use(Farmer farmer) {
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();

                if(tile.isAvailable()){
                    ts.setFeedback("\nTile is empty.");
                    return ts;
                }

                // check if tile has no rocks
                if(!tile.getTileStatus().getReason().contains("rocks") || 
                   !tile.getTileStatus().getReason().contains("Rocks")){
                    ts.setFeedback("\nNo rocks to mine.");
                    return ts;
                }

                // check if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("\nNot enough money to mine rocks.");
                    return ts;
                }

                // update tilestatus
                tile.makeAvailable();
                ts.setFeedback("\nSuccessfully removed rocks from tile.");

                // give exp
                farmer.giveExp(this.getExpGain());
                // take money
                farmer.updateCoins(-1 * this.getCost());

                ts.setSuccess(true);

                return ts;
            }
        };
    }

    /**
     * Method that initializes the shovel tool
     */
    private void initShovel(){

        this.shovel = new Tool(7.0, 2.0){

            @Override
            public ToolStatus use(Farmer farmer){
                Tile tile = farmer.getFreeTile();
                ToolStatus ts = new ToolStatus();
                ts.setFeedback("\nFailed to dig up crop.");
                // check if tile has a withered plant
                if (tile.getCrop() == null)
                    return ts;

                if (tile.isAvailable()){
                    ts.setFeedback("\nTile is empty.");
                }


                if(!tile.getCrop().isWithered()){
                    ts.setFeedback("\nCrop is not withered.");
                    return ts;
                }

                if (farmer.getObjectCoins() < this.getCost()){
                    ts.setFeedback("\nNot enough money to dig up crop.");
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

    /**
     * Gets the name of the farmer
     * @return name of the farmer
     */
    public String getName() {

        return this.name;
    }

    /**
     * Gets the farmer's level and level title
     * @return level of the farmer
     */
    public FarmerLevel getFarmerLevel() {
        return this.level;
    }

    /**
     * Gets the farmer's experience points
     * @return experience points of the farmer
     */
    public double getExp() {
        return this.exp;
    }

    /**
     * Gets the farmer's coins
     * @return the farmer's objectCoins
     */
    public double getObjectCoins() {
        return this.objectCoins;
    }

    /**
     * Gets the next tile that the farmer can interact with
     * @return the next tile that the farmer can interact with
     */
    public Tile getFreeTile() {
        return this.freeTile;
    }
    
    /**
     * Decreases the money of the farmer when a seed is bought
     * @param seed the seed to be bought by the farmer
     */
    public void buySeed(Seed seed) {
        this.objectCoins -= seed.getCost();
    }

    /**
     * Allows the farmer to plant a seed on an available tile 
     * @param seed the seed to be planted
     */
    public void plantSeed(Seed seed) {
        buySeed(seed);
        this.freeTile.setCrop(new Crop(seed.getName()));
        System.out.println("\nYou have planted a " + seed.getName() + "!");

        this.freeTile.getTileStatus().hasCrop();
    }

    /**
     * Updates the money of the farmer when a crop is harvested
     * @returns true if the crop is successfully harvested and false otherwise
     */
    public boolean harvestCrop() {
        if(freeTile.getCrop() == null)
            return false;

        Crop crop = freeTile.getCrop();

        if(!crop.isHarvestable() && !crop.isWithered() || crop.isWithered())
            return false;

        int yield = crop.getHarvestYield();

        System.out.println("\nYou have harvested " + yield + " " + crop.getName() + "(s)!");

        double harvestTotal = yield * (crop.getBasePrice());
        double waterBonus = harvestTotal * 0.2 * (crop.getTimesWatered() - 1);
        double fertilizerBonus = harvestTotal * 0.5 * crop.getTimesFertilized();
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        // Add coins to wallet
        this.objectCoins += finalHarvestPrice;

        System.out.println("You have earned " + finalHarvestPrice + " coins!");

        // Set tile to empty
        freeTile.setCrop(null);
        freeTile.setPlowed(false);
        freeTile.makeAvailable();

        return true;
    }

    /**
     * Updates the exp of the farmer
     * @param expGain the amount of exp that the farmer has gained
     */
    public void giveExp(double expGain) {
        this.exp += expGain;
    }

    /**
     * Updates the money of the fatmer
     * @param amount the amount of money that the farmer has gained or lost
     */
    public void updateCoins(double amount) {
        this.objectCoins += amount;
    }
}
