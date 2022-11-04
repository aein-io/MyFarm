public class Turnip {
    private String name, type;
    private double cost, basePrice;
    private int timesWatered, timesFertilized;
    private int waterNeeds, fertilizerNeeds;
    private int waterBonus, fertilizerBonus;
    private int harvestTime, harvestMin, harvestMax;

    public Turnip() {
        this.name = "Turnip";
        this.type = "Root Crop";
        this.cost = 5.0;
        this.basePrice = 6.0;
        this.timesWatered = 0;
        this.timesFertilized = 0;
        this.waterNeeds = 1;
        this.fertilizerNeeds = 0;
        this.harvestTime = 2;
        this.harvestMin = 1;
        this.harvestMax = 2;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getCost() {
        return this.cost;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public int getTimesWatered() {
        return this.timesWatered;
    }

    public int getTimesFertilized() {
        return this.timesFertilized;
    }

    public int getWaterNeeds() {
        return this.waterNeeds;
    }

    public int getFertilizerNeeds() {
        return this.fertilizerNeeds;
    }

    public int getHarvestTime() {
        return this.harvestTime;
    }

    public int getHarvestMin() {
        return this.harvestMin;
    }

    public int getHarvestMax() {
        return this.harvestMax;
    }

    public int getProduce() {
        return (int) (Math.random() * (getHarvestMax() - getHarvestMin() + 1) + getHarvestMin());
    }

    public int addWater() {
        return this.timesWatered += 1;
    }

    public int addFertilizer() {
        return this.timesFertilized += 1;
    }

    public void growTurnip() {
        // Check if water needs are met
        // Check if fertilizer needs are met
        // Wither or harvest
    }
}


