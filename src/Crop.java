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
                this.fertilizerNeeds = 3;
                this.fertilizerBonus = 2;
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

    public void water(){
        timesWatered++;
    }

    public int getTimesWatered(){
        return timesWatered;
    }

    public int getWaterBonus(){
        return waterBonus;
    }

    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

    public void fertilize() {
        timesFertilized++;
    }

    public boolean isWithered() {
        return isWithered;
    }

    public int getHarvestYield() {
        int yield = (int) (Math.random() * (harvestYieldMax - harvestYieldMin + 1) + harvestYieldMin);
        return yield;
    }

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getWaterNeeds() {
		return waterNeeds;
	}

	public int getTimesFertilized() {
		return timesFertilized;
	}

	public int getFertilizerNeeds() {
		return fertilizerNeeds;
	}

	public double getExpGain() {
		return expGain;
	}

	public int getHarvestTime() {
		return harvestTime;
	}

	public int getPlantedSince() {
		return plantedSince;
	}

	public boolean isHarvestable() {
		return isHarvestable;
	}

    public double getBasePrice() {
        return basePrice;
    }

    public void updatePlantedSince() {
        plantedSince++;
    }

    public int plantedWhen(){
        return plantedSince;
    }

    public void wither(){
        this.isWithered = true;
    }

    public void setHarvestable(boolean b) {
        this.isHarvestable = b;
    }
}
