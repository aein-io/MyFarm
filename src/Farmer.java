import java.util.Scanner;

public class Farmer {
    private String name;
    private double objectCoins;
    private Turnip crop;
    private int plantedSeeds, harvestedCrops;
    private Tool plow, wateringCan, fertilizer;

    Scanner sc = new Scanner(System.in);

    public Farmer() {
        this.name = "Jack";
        this.objectCoins = 100;
    }

    public Farmer(String name) {
        this.name = name;
        this.objectCoins = 100;
    }

    public String getName() {
        return this.name;
    }

    public double getObjectCoins() {
        return this.objectCoins;
    }

    public void plow(Tile tile) {
        if (!tile.plowed()) {
            plow.use(tile);
        }
    }
    
    public void plant(Tile tile) {
        if(tile.plowed()) {
            buy(crop);
            System.out.println("Seed has been planted!");
        }

        else
            System.out.println("There is no plowed land.");
    }

    public void buy(Turnip crop) {
        System.out.print("\033[H\033[2J");
        System.out.println("\nThis is the Seed Store!");
        System.out.println("Looks like they’re still stocking up for the new season.");
        System.out.println("Luckily, they still have some TURNIPS.");

        System.out.println("\nName\tCost\tHarvest Time");
        System.out.println(crop.getName() + "\t" + crop.getCost() + "\t" + crop.getHarvestTime());

        System.out.println("\nWhy don't you buy some?");

        System.out.print("\nPress [B] to BUY or [X] to RETURN: ");

        char buy = sc.next().charAt(0);

        this.objectCoins = this.objectCoins - crop.getCost();

        System.out.println("\nThank you for your purchase!");
        System.out.println("You currently have " + getObjectCoins() + " Objectcoins.");
    }
    
    public void water(Turnip crop) {

    }

    public void fertilize(Turnip crop) {

    }

    public void harvest(Tile tile) {
        if(tile.harvestable()) {
            // Harvest crop
        }
    }
}
