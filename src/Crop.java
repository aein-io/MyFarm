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

    public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

    public int getTimesWatered(){
        return timesWatered;
    }

	public int getWaterNeeds() {
		return waterNeeds;
	}

    public int getWaterBonus(){
        return waterBonus;
    }

	public int getTimesFertilized() {
		return timesFertilized;
	}

	public int getFertilizerNeeds() {
		return fertilizerNeeds;
	}

    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

	public double getExpGain() {
		return expGain;
	}

	public int getHarvestTime() {
		return harvestTime;
	}

    /**
     * @return A random integer between the minimum harvest and the maximum harvest
     */
    public int getHarvestYield() {
        int yield = (int) (Math.random() * (harvestYieldMax - harvestYieldMin + 1) + harvestYieldMin);
        return yield;
    }
    
    public double getBasePrice() {
        return basePrice;
    }

	/**
	 * @return The number of days the plant has been planted for
	 */
    public int plantedWhen(){
        return plantedSince;
    }
    
    public boolean isWithered() {
        return isWithered;
    }
	
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
     * Changes the isHarvestable state of the crop
     * @param b Whether the crop is harvestable
     */
    public void setHarvestable(boolean b) {
        this.isHarvestable = b;
    }
    
    /**
     * Adds to the number of days the crop has been planted for
     */
    public void updatePlantedSince() {
        plantedSince++;
    }
}
