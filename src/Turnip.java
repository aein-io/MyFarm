import java.util.*;

public class Turnip {
    private String name, type;
    private double cost, basePrice;
    private int timesWatered, timesFertilized;
    private int waterNeeds, fertilizerNeeds;
    private int waterBonus, fertilizerBonus;
    private int harvestTime, harvestMin, harvestMax;

    private Tile tile;
    
    public Turnip() {
        this.name = "Turnip";
        this.type = "Root Crop";
        this.cost = 5.0;
        this.basePrice = 6.0;
        this.timesWatered = 0;
        this.timesFertilized = 0;
        this.waterNeeds = 1;
        this.fertilizerNeeds = 0;
        this.waterBonus = 2;
        this.fertilizerBonus = 1;
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

    public int getWaterBonus() {
        return this.waterBonus;
    }

    public int getFertilizerBonus() {
        return this.fertilizerBonus;
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

    public int getProduced() {
        return (int) (Math.random() * (getHarvestMax() - getHarvestMin() + 1) + getHarvestMin());
    }

    public int addWater() {
        return this.timesWatered += 1;
    }

    public int addFertilizer() {
        return this.timesFertilized += 1;
    }

    public void growCrop(Turnip crop) {
        
        Scanner sc = new Scanner(System.in);
        
        // No crop is planted
        if (tile.getCrop() == null) {
            System.out.println("\n< Daily Progress >");
            System.out.println("There are no seeds planted.");
            
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
        
        // Crop progress
        else {
            System.out.println("\n< Daily Progress >");
            System.out.println("Times Watered: " + getTimesWatered());
            System.out.println("Times Fertilized: " + getTimesFertilized());

            // Crop is harvestable
            if (getHarvestTime() == tile.getDayCount() &&  (getTimesWatered() >= getWaterNeeds() || getTimesFertilized() >= getFertilizerNeeds())) {
                tile.isHarvestable();
                System.out.println("\n\nGood job! Your crop is ready for harvesting!");
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }

            // Crop has withered
            else if (getHarvestTime() == tile.getDayCount() &&  (getTimesWatered() < getWaterNeeds() || getTimesFertilized() < getFertilizerNeeds())) {
                tile.isWithered();
                System.out.println("\n\nOh no! Your crop has withered.");
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }

            // Crop is still growing
            else {
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }
        }
    }
}


