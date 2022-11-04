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

        if(tile.isPlowed() && tile.getCrop() == null) {
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

        int produced = tile.getCrop().getProduce();
        double harvestTotal = produced * (tile.getCrop().getBasePrice());

        double waterBonus = 0;
        double fertilizerBonus = 0;

        // Check if water needs are met
        // Check if fertilizer needs are met

        // Add coins to wallet
        this.objectCoins += harvestTotal;

        // Make a new tile class (to set it back to null)
        new Tile();

        // Successfully harvested crop
    }

    public void useWateringCan(Tile tile) {
        
        if(tile.getCrop() != null) {
            this.objectCoins -= this.wateringCan.getCost();
            this.wateringCan.waterCrop(tile);
            System.out.println("\nYou have watered the " + tile.getCrop().getName() + 
                " this many times: " + tile.getCrop().getTimesWatered());
            
            int left = tile.getCrop().getWaterNeeds() - tile.getCrop().getTimesWatered();
            System.out.println("Remaining water times required: " + left);

            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

        else {
            System.out.println("\nThis tile has no crop to water!");
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
    }

    public void useFertilizer(Tile tile) {
        
        if(tile.getCrop() != null) {
            this.objectCoins -= this.fertilizer.getCost();
            this.fertilizer.fertilizeCrop(tile);
            System.out.println("\nYou have fertilized the " + tile.getCrop().getName() + 
                " this many times: " + tile.getCrop().getTimesFertilized());
            
            int left = tile.getCrop().getFertilizerNeeds() - tile.getCrop().getTimesFertilized();
            System.out.println("Remaining fertilize times required: " + left);

            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }

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

