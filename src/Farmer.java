import java.util.ArrayList;
import java.util.*;

public class Farmer {

    private String name;
    private double objectCoins;

    private ArrayList<Turnip> seedArray;
    private WateringCan wateringCan;
    private Plow plow;
    private Fertilizer fertilizer;
    
    Scanner sc = new Scanner(System.in);

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

        return this.seedArray;
    }

    public void buySeed(Turnip seed) {

        this.seedArray.add(seed);
        this.objectCoins -= seed.getCost();
    }

    public void plantSeed(Turnip seed, Tile tile) {

        if (tile.isPlowed() && tile.getCrop() == null) {
            buySeed(seed);
            tile.setCrop(new Turnip());
            System.out.println("\nYou have planted a " + seed.getName() + "!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

        else {
            System.out.println("\nThis tile has not been plowed OR already has a seed!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
    }

    public void harvestCrop(Tile tile) {

        Turnip crop = tile.getCrop();
        
        int produced = crop.getProduced();
        double harvestTotal = produced * (crop.getBasePrice());
        double waterBonus = harvestTotal * 0.2 * (crop.getTimesWatered() - 1);
        double fertilizerBonus = harvestTotal * 0.5 * crop.getTimesFertilized();
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;      

        // Add coins to wallet
        this.objectCoins += finalHarvestPrice;
        new Tile();

        // Successfully harvested crop
    }

    public void useWateringCan(Tile tile) {
        
        Turnip crop = tile.getCrop();
        
        // Waters the crop
        if (crop != null && crop.getTimesWatered() < crop.getWaterBonus()) {
            this.objectCoins -= this.wateringCan.getCost();
            this.wateringCan.waterCrop(tile);
            System.out.println("\nYou have watered the " + crop.getName() + 
                " this many times: " + crop.getTimesWatered());
            
            // Uses waterBonus instead of waterNeeds to track the limit
            int left = crop.getWaterBonus() - crop.getTimesWatered();
            System.out.println("Remaining water tries: " + left);

            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

        // Reached limit of watering times
        else if (crop != null && crop.getTimesWatered() == crop.getWaterBonus()) {
            System.out.println("\nYou have reached the maximum number of times to water a " + crop.getName() + "!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
        
        // No crop to water
        else {
            System.out.println("\nThis tile has no crop to water!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
    }

    public void useFertilizer(Tile tile) {
        
        Turnip crop = tile.getCrop();
        
        // Fertilizes the crop
        if (crop != null && crop.getTimesFertilized() < crop.getFertilizerBonus()) {
            this.objectCoins -= this.fertilizer.getCost();
            this.fertilizer.fertilizeCrop(tile);
            System.out.println("\nYou have fertilized the " + crop.getName() + 
                " this many times: " + crop.getTimesFertilized());
            
            // Uses fertilizerBonus instead of fertilizerNeeds to track the limit
            int left = crop.getFertilizerBonus() - crop.getTimesFertilized();
            System.out.println("Remaining fertilize times required: " + left);

            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

        // Reached limit of fertilizing times
        else if (crop != null && crop.getTimesFertilized() == crop.getFertilizerBonus()) {
            System.out.println("\nYou have reached the maximum number of times to fertilize a " + crop.getName() + "!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

        // No crop to fertilize
        else {
            System.out.println("\nThis tile has no crop to fertilize!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

    }

    public void usePlow(Tile tile) {
        if(!tile.isPlowed())
            this.plow.plowTile(tile);

        else {
            System.out.println("\nThis tile has already been plowed!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
    }
}

