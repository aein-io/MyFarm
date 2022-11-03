import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();
        Turnip crop = new Turnip();

        // Start
        System.out.println("\nDAY 1"); // Need to put a counter here

        System.out.println("\nWelcome, Farmer " + farmer.getName() + ".");
        System.out.println("You currently have " + farmer.getObjectCoins() + " Objectcoins.");

        System.out.println("\nWhat would you like to do?");
        System.out.println("[1] Sleep");
        System.out.println("[2] Buy");
        System.out.println("[1] Farm");
        System.out.print("\nInput: ");

        int input = sc.nextInt();

        switch (input) {
            case 1 -> System.out.println(1);
            case 2 -> {
                System.out.print("\033[H\033[2J");
                farmer.buySeed(crop); }
            case 3 -> System.out.println(3);
        }

        sc.close();
    }
}