/**
 * This class represents a crop in the game.
 * 
 * @version 1.0
 */
public class Crop {
    private String name;
    private String type;

    private int timesWatered;
    private int waterNeeds;
    private int waterBonus;

    private int timesFertilized;
    private int fertilizerNeeds;
    private int fertilizerBonus;

    private double basePrice;
    private double expGain;

    private int harvestTime;
    private int plantedSince;
    private int harvestYieldMin;
    private int harvestYieldMax;

    private boolean isHarvestable;
    private boolean isWithered;

    /**
     * Initializes a crop with their default values.
     */
    public Crop(String seedName) {
        switch(seedName) {
            case "Turnip" :
                this.name = "Turnip";
                this.type = "Root Crop";
                this.timesWatered = 0;
                this.waterNeeds = 1;
                this.waterBonus = 2;
                this.timesFertilized = 0;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.basePrice = 6.00;
                this.expGain = 5.00;
                this.harvestTime = 2;
                this.plantedSince = 0;
                this.harvestYieldMin = 1;
                this.harvestYieldMax = 2;
                this.isHarvestable = false;
                this.isWithered = false;
                break;
            case "Carrot" :
                this.name = "Carrot";
                this.type = "Root Crop";
                this.timesWatered = 0;
                this.waterNeeds = 1;
                this.waterBonus = 2;
                this.timesFertilized = 0;
                this.fertilizerNeeds = 0;
                this.fertilizerBonus = 1;
                this.basePrice = 9.0;
                this.expGain = 7.5;
                this.harvestTime = 3;
                this.plantedSince = 0;
                this.harvestYieldMin = 1;
                this.harvestYieldMax = 2;
                this.isHarvestable = false;
                this.isWithered = false;
                break;
        }
    }

    /**
     * Gets the name of a crop.
     * 
     * @return name of crop
     */
    public String getName() {
		return name;
	}

	/**
     * Gets the type of a crop.
     * 
     * @return type of crop
     */
    public String getType() {
		return type;
	}

    /**
     * Gets the number of times a crop is watered.
     * 
     * @return times a crop is watered
     */
    public int getTimesWatered(){
        return timesWatered;
    }

    /**
     * Gets the required times a crop must be watered.
     * 
     * @return water requirement
     */
	public int getWaterNeeds() {
		return waterNeeds;
	}

    /**
     * Gets the maximum times a crop can be watered.
     * 
     * @return maximum water times
     */
    public int getWaterBonus(){
        return waterBonus;
    }

    /**
     * Gets the number of times a crop is fertilized.
     * 
     * @return times a crop is fertilized
     */
    public int getTimesFertilized() {
		return timesFertilized;
	}

    /**
     * Gets the required times a crop must be fertilized.
     * 
     * @return fertilized requirement
     */
	public int getFertilizerNeeds() {
		return fertilizerNeeds;
	}

    /**
     * Gets the maximum times a crop can be fertilized.
     * 
     * @return maximum fertilizer times
     */
    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

	/**
     * Gets experience gained from harvesting a crop.
     * 
     * @return experience from crop
     */
    public double getExpGain() {
		return expGain;
	}

    /**
     * Gets the harvest time of a crop.
     * 
     * @return crop's harvest time
     */
	public int getHarvestTime() {
		return harvestTime;
	}

    /**
     * Gets random number of produced crops from one harvest.
     * 
     * @return A random integer between the minimum harvest and the maximum harvest
     */
    public int getHarvestYield() {
        int yield = (int) (Math.random() * (harvestYieldMax - harvestYieldMin + 1) + harvestYieldMin);
        return yield;
    }
    
    /**
     * Gets base price of a crop.
     * 
     * @return crop's base price
     */
    public double getBasePrice() {
        return basePrice;
    }

	/**
	 * Gets the number of days a crop has been planted.
     * 
     * @return the number of days the crop has been planted for
	 */
    public int plantedWhen(){
        return plantedSince;
    }
    
    /**
     * Checks if a crop has withered.
     * 
     * @return true if crop has withered, false otherwise
     */
    public boolean isWithered() {
        return isWithered;
    }
	
	/**
     * Checks if a crop is harvestable.
     * 
     * @return true is crop is harvestable, false otherwise
     */
    public boolean isHarvestable() {
		return isHarvestable;
	}

    /** 
     * Increments timesWatered by 1
     */
    public void water(){
        timesWatered++;
    }

    /**
     * Increments timesFertilized by 1
     */
    public void fertilize() {
        timesFertilized++;
    }

    /**
     * Sets the plant's wither status to true
     */
    public void wither(){
        this.isWithered = true;
    }

    /**
     * Changes the isHarvestable state of the crop.
     * 
     * @param b Whether the crop is harvestable
     */
    public void setHarvestable(boolean b) {
        this.isHarvestable = b;
    }
    
    /**
     * Adds to the number of days the crop has been planted for.
     */
    public void updatePlantedSince() {
        plantedSince++;
    }
}
