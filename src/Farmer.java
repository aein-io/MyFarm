import java.util.ArrayList;
import java.util.*;

/**
 * This class represents the farmer in the game.
 * 
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
     * Constructor that initializes the farmer with default values and tools.
     * 
     * @param name the name of the farmer
     * @param freetile the next available tile that the farmer can interact with
     */
    public Farmer(String name, Tile freetile) {

        this.name = name;
        this.freeTile = freetile; 
        this.objectCoins = 100;
        this.exp = 0;
        this.level = new FarmerLevel();

        // Gives this.wateringCan appropriate attributes and functions
        initWateringCan();

        // Gives this.plow appropriate attributes and functions
        initPlow();

        // Gives this.fertilizer appropriate attributes and functions
        initFertilizer();

        // Gives this.shovel appropriate attributes and functions
        initShovel();

        // Gives this.pickaxe appropriate attributes and functions
        initPickaxe();
    }

    /**
     * Uses the plow tool.
     * 
     * @param farmer the player of the game
     * @returns a call to the plow.use method containing use success and context
     */
    public ToolStatus usePlow(Farmer farmer) {
        return this.plow.use(farmer);
    }

    /**
     * Uses the watering can tool.
     * 
     * @param farmer the player of the game
     * @returns a call to the wateringCan.use method containing use success and context
     */
    public ToolStatus useWateringCan(Farmer farmer) {
        return this.wateringCan.use(farmer);
    }

    /**
     * Uses the fertilizer tool.
     * 
     * @param farmer the player of the game
     * @returns a call to the fertilizer.use method containing use success and context
     */
    public ToolStatus useFertilizer(Farmer farmer) {
        return this.fertilizer.use(farmer);
    }

    /**
     * Uses the pickaxe tool.
     * 
     * @param farmer the player of the game
     * @returns a call to the pickaxe.use method containing use success and context
     */
    public ToolStatus usePickaxe(Farmer farmer) {
        return this.pickaxe.use(farmer);
    }
    
    /**
     * Uses the shovel tool.
     * 
     * @param farmer the player of the game
     * @returns a call to the shovel.use method containing use success and context
     */
    public ToolStatus useShovel(Farmer farmer) {
        return this.shovel.use(farmer);
    }

    /**
     * Initializes the plow tool.
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

                    // Give exp to farmer
                    farmer.giveExp(this.getExpGain());
                    ts.setFeedback("\nTile successfully plowed.");
                    ts.setSuccess(true);
                }

                return ts;
            }
        };
    }

    /**
     * Initializes the watering can tool
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

                // Checks if crop is withered
                if (tile.getCrop().isWithered()) {
                    ts.setFeedback("\nWithered crop cannot be watered.");
                    return ts;
                }

                // Checks if crop is harvestable
                if (tile.getCrop().isHarvestable()) {
                    ts.setFeedback("\nThere is no need to fertilize a grown crop.");
                    return ts;
                }
                
                if (tile.getCrop() != null) {
                    if (timesWatered < waterBonus + waterBonusInc) {
                        tile.getCrop().water();
                        ts.setFeedback("\nCrop successfully watered.");
                        ts.setSuccess(true);
                        farmer.giveExp(this.getExpGain());
                    }
                    else if (timesWatered == waterBonus + waterBonusInc) {
                        ts.setFeedback("\nCrop's water bonus has been reached.");
                    }
                }

                return ts;
            }
        };
    }

    /**
     * Initializes the fertilizer tool
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

                // Checks if tile has a crop
                if (tile.getCrop() == null) {
                    ts.setFeedback("\nNo crop to fertilize.");
                    return ts;
                }

                // Checks if crop is withered
                if (tile.getCrop().isWithered()){
                    ts.setFeedback("\nWithered crop cannot be fertilized.");
                    return ts;
                }

                // Checks if crop is harvestable
                if (tile.getCrop().isHarvestable()) {
                    ts.setFeedback("\nThere is no need to water a grown crop.");
                    return ts;
                }

                // Checks if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("\nNot enough money to fertilize crop.");
                    return ts;
                }
                
                // Checks if bonus is reached
                if (timesFertilized == fertilizerBonus + fertilizerBonusInc) {
                    ts.setFeedback("\nCrop's fertilizer bonus has been reached.");
                }
                
                // Successfully fertilize crop
                if (timesFertilized < fertilizerBonus + fertilizerBonusInc) {
                    tile.getCrop().fertilize();
                    ts.setFeedback("\nCrop successfully fertilized.");

                    // Gives farmer exp
                    farmer.giveExp(this.getExpGain());
                    // Deducts coins accordingly
                    farmer.updateCoins(-1 * this.getCost());
                    ts.setSuccess(true);
                }

                return ts;
            }
        };
    }

    /**
     * Initializes the pickaxe tool.
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

                // Checks if tile has no rocks
                if(!tile.getTileStatus().getReason().contains("rocks") || 
                   !tile.getTileStatus().getReason().contains("Rocks")){
                    ts.setFeedback("\nNo rocks to mine.");
                    return ts;
                }

                // Checks if farmer has enough money
                if (farmer.getObjectCoins() < this.getCost()) {
                    ts.setFeedback("\nNot enough money to mine rocks.");
                    return ts;
                }

                // Updates tilestatus
                tile.makeAvailable();
                ts.setFeedback("\nSuccessfully removed rocks from tile.");

                farmer.giveExp(this.getExpGain());
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
                
                // Check if tile has a withered plant
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
                freeTile.setPlowed(false);
                tile.makeAvailable();

                farmer.giveExp(this.getExpGain());
                farmer.updateCoins(-1 * this.getCost());

                ts.setSuccess(true);

                return ts;
            }
        };
    }

    /**
     * Gets the name of the farmer.
     * 
     * @return name of the farmer
     */
    public String getName() {

        return this.name;
    }

    /**
     * Gets the farmer's level and level title.
     * 
     * @return level of the farmer
     */
    public FarmerLevel getFarmerLevel() {
        return this.level;
    }

    /**
     * Gets the farmer's experience points.
     * 
     * @return experience points of the farmer
     */
    public double getExp() {
        return this.exp;
    }

    /**
     * Gets the farmer's coins.
     * 
     * @return the farmer's objectCoins
     */
    public double getObjectCoins() {
        return this.objectCoins;
    }

    /**
     * Gets the next tile that the farmer can interact with.
     * 
     * @return free tile that a farmer can use
     */
    public Tile getFreeTile() {
        return this.freeTile;
    }
    
    /**
     * Deducts the Objectcoins of the farmer when a seed is bought.
     * 
     * @param seed the seed to be bought by the farmer
     */
    public void buySeed(Seed seed) {
        this.objectCoins -= seed.getCost();
    }

    /**
     * Let's farmer plant a seed on an available tile.
     * 
     * @param seed the seed to be planted
     */
    public void plantSeed(Seed seed) {
        buySeed(seed);
        this.freeTile.setCrop(new Crop(seed.getName()));
        System.out.println("\nYou have planted a " + seed.getName() + "!");

        this.freeTile.getTileStatus().hasCrop();
    }

    /**
     * Updates the money of the farmer when a crop is harvested.
     * 
     * @returns true if the crop is successfully harvested, false otherwise
     */
    public boolean harvestCrop() {
        
        if (freeTile.getCrop() == null) {
            System.out.println("\nNo crop to harvest!");
            return false;
        }
        
        Crop crop = freeTile.getCrop();

        if (!crop.isHarvestable() && !crop.isWithered()) {
            System.out.println("\nCrop is not yet harvestable!");
            return false;
        }

        if (crop.isWithered()) {
            System.out.println("\nWithered crop cannot be harvested.");
            return false;
        }
            

        int yield = crop.getHarvestYield();

        System.out.println("\nYou have harvested " + yield + " " + crop.getName() + "(s)!");

        double harvestTotal = yield * (crop.getBasePrice());
        double waterBonus = harvestTotal * 0.2 * (crop.getTimesWatered() - 1);
        double fertilizerBonus = harvestTotal * 0.5 * crop.getTimesFertilized();
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        // Add coins to wallet
        this.objectCoins += finalHarvestPrice;

        System.out.println("You have earned " + finalHarvestPrice + " coins!");

        // Set tile to empty and unplowed
        freeTile.setCrop(null);
        freeTile.setPlowed(false);
        freeTile.makeAvailable();

        return true;
    }

    /**
     * Updates the exp of the farmer.
     * 
     * @param expGain the amount of exp that the farmer has gained
     */
    public void giveExp(double expGain) {
        this.exp += expGain;
    }

    /**
     * Updates the Objectcoins of the farmer.
     * 
     * @param amount the amount of money that the farmer has gained or lost
     */
    public void updateCoins(double amount) {
        this.objectCoins += amount;
    }
}
