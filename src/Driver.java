import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Farmer farmer = new Farmer();
        Turnip crop = new Turnip();

        // Start
        System.out.println("\nWelcome to MyFarm!");

        System.out.println("\nLet's get your started, Farmer " + farmer.getName() + ".");
        System.out.println("Here is " + farmer.getObjectCoins() + " Objectcoins!");

        System.out.println("\nWhat would you like to do?");
        System.out.println("[1] Buy");
        System.out.println("[2] Plant");
        System.out.println("[3] Water");
        System.out.println("[4] Fertilize");
        System.out.println("[5] Harvest");
        System.out.print("\nInput: ");

        int input = sc.nextInt();

        switch (input) {
            case 1 -> farmer.buySeed(crop);
            case 2 -> System.out.println(2);
            case 3 -> System.out.println(3);
            case 4 -> System.out.println(4);
            case 5 -> System.out.println(5);
            default -> {
            }
        }


    }
}
