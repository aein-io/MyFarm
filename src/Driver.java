import java.util.*;

public class Driver {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();
        Turnip crop = new Turnip();
        Tile tile = new Tile();

        boolean exit = false;

        while(!exit) {

            // Start
            System.out.print("\033[H\033[2J");
            System.out.println("\nDAY " + tile.getDayCount());

            System.out.println("\nWelcome, Farmer " + farmer.getName() + ".");
            System.out.println("You currently have " + farmer.getObjectCoins() + " Objectcoins.\n");

            // Displays Farm
            System.out.println(tile.display());
            
            System.out.println("\nWhat would you like to do?");
            System.out.println("[1] Sleep");
            System.out.println("[2] Plant");
            System.out.println("[3] Plow");
            System.out.println("[4] Exit");
            System.out.print("\nInput: ");

            // Kind of want to put a report here?

            int input = sc.nextInt();

            switch (input) {
                case 1 -> tile.advanceDay();
                case 2 -> farmer.plant(tile);
                case 3 -> farmer.plow(tile);
                case 4 -> exit = true;
            }
        }

        sc.close();
    }
}