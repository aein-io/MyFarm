import java.util.*;

public class Driver {

    public static void seedStore() {
        Turnip crop = new Turnip();
        
        // Put an array for Seeds here 
        System.out.print("\033[H\033[2J");
        System.out.println("\n< The Seed Store >");
        System.out.println("\nName\tCost\tHarvest Time");
        System.out.println(crop.getName() + "\t" + crop.getCost() + "\t" + crop.getHarvestTime());
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();
        Tile tile = new Tile();
        Turnip crop = new Turnip();

        boolean exit = false;

        while(!exit) {

            // Start
            System.out.print("\033[H\033[2J");
            System.out.println("\nDAY " + tile.getDayCount());

            System.out.println("\nWelcome, Farmer " + farmer.getName() + ".");
            System.out.println("You currently have " + farmer.getObjectCoins() + " Objectcoins.\n");

            // Displays Farm
            System.out.println(tile.display());

            // Gives feedback
            
            System.out.println("\nWhat would you like to do?");
            System.out.println("[1] Sleep\t [4] Water");
            System.out.println("[2] Plow\t [5] Fertilize");
            System.out.println("[3] Plant\t [6] Harvest");
            System.out.println("\n[0] Exit");
            System.out.print("\nInput: ");
            
            int input = sc.nextInt();

            switch (input) {
                case 0 : 
                    exit = true;
                    break;
                case 1 : 
                    tile.advanceDay();
                    break;
                case 2 : 
                    farmer.plow(tile);
                    break;
                case 3 : 
                    seedStore();
                    System.out.print("\nPress [B] to buy TURNIPS: ");
                    char store = sc.next().charAt(0);
                    farmer.buy(crop);
                    farmer.plant(tile);
                    break;
            }
        }

        sc.close();
    }
}