public class Turnip {
    private String name, type;
    private double cost, basePrice;
    private int timesWatered, timesFertilized;
    private int waterNeeds, fertilizerNeeds;
    private int harvestTime, harvestYieldMin, harvestYieldMax;

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
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 2;
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

    public int getHarvestYieldMin() {
        return this.harvestYieldMin;
    }

    public int getHarvestYieldMax() {
        return this.harvestYieldMax;
    }

    public void growTurnip() {
        // Check if water needs are met
        // Check if fertilizer needs are met
        // Wither or harvest
    }
}

