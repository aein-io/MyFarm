import java.util.*;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();
        Tile tile = new Tile();
        Turnip crop = new Turnip();

        boolean exit = false;

        while(!exit) {

            // Start
            System.out.print("\033[H\033[2J");
            System.out.println("DAY " + tile.getDayCount());

            System.out.println("\nWelcome, Farmer " + farmer.getName() + ".");
            System.out.println("You currently have " + farmer.getObjectCoins() + " Objectcoins.\n");

            // Displays Farm
            System.out.println(tile.display());

            // Displays Information (Might be better to have default values?)
            if (tile.getCrop() != null) {
                System.out.println("\nName: " + crop.getName() + "\t\tWater Needs: " + crop.getWaterNeeds());
                System.out.println("Type: " + crop.getType() + "\t\tFertilizer Needs: " + crop.getFertilizerNeeds());
            }
            
            // Tasks
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
                    tile.growCrop();
                    break;
                case 2 :
                    farmer.usePlow(tile);
                    break;
                case 3 :
                    farmer.plantSeed(crop, tile);
                    break;
                case 4 :
                    farmer.useWateringCan(tile);
                    break;
                case 5 :
                    farmer.useFertilizer(tile);
                    break;
                case 6 :
                    farmer.harvestCrop(tile);
                    break;
            }

        }

        sc.close();
    }
}
