import java.util.Scanner;

/**
 * This clas is the driver of the program
 * @version 1.0
 */
public class Driver {

    public static void main(String[] args) {

        int days = 0;

        FarmPlot farm = new FarmPlot("Prototype Farms", 1, 1);
        Farmer farmer = new Farmer("Tester", farm.getTile(0, 0));

        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        ToolStatus feedback = new ToolStatus();

        while (!exit) {

            // Start
            System.out.print("\033[H\033[2J");
            System.out.println("DAY " + (days + 1));
            System.out.println("\nWelcome, " + farmer.getFarmerLevel().getLevelTitle() + " " + farmer.getName() + ".");

            // Tasks
            int input = -1;
            while (input != 7 && input != 0) {
                System.out.println("You currently have " + farmer.getObjectCoins() + " Objectcoins.\n");
                System.out.println("Exp: " + farmer.getExp());
                System.out.println("Level: " + farmer.getFarmerLevel().getCurrentLevel());
                System.out.println("--------------------");
                System.out.println();
                farm.displayFarmPlot();
                System.out.println();
                System.out.println("\nWhat would you like to do?");
                System.out.println("[1] Plow\t [4] Fertilize");
                System.out.println("[2] Plant\t [5] Harvest");
                System.out.println("[3] Water\t [6] Use Shovel");

                System.out.println("\n[7] Sleep");
                System.out.println("[0] Exit");
                System.out.print("\nInput: ");

                input = sc.nextInt();

                switch (input) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        feedback = farmer.usePlow(farmer);
                        System.out.println(feedback.getFeedback());
                        break;
                    case 2:
                        // check if the farmer is standing on a tile that currently has a crop
                        if (farmer.getFreeTile().isAvailable() == false) {
                            System.out.println("\nYou can't plant a seed on a tile that is occupied!");
                            break;
                        }

                        // check if the farmer is standing on a tile that is not plowed
                        if (farmer.getFreeTile().isPlowed() == false) {
                            System.out.println("\nYou can't plant a seed on a tile that is not plowed!");
                            break;
                        }

                        // show a selection of seeds to the farmer
                        // farmer selects a seed
                        // farmer uses seed to plant
                        System.out.println();
                        farm.printAvailableSeeds();
                        System.out.println("\nSelect a seed to plant: ");
                        int seed = sc.nextInt();
                        if (seed < 0 || seed >= farm.getAvailableSeeds().size()) {
                            System.out.println("Invalid input.");
                            break;
                        }

                        // check if farmer has enough money
                        if (farmer.getObjectCoins() < farm.getAvailableSeeds().get(seed).getCost()) {
                            System.out.println("You don't have enough money to buy this seed.");
                            break;
                        }

                        // plant the seed
                        farmer.plantSeed(farm.getAvailableSeeds().get(seed));
                        break;
                    case 3:
                        feedback = farmer.useWateringCan(farmer);
                        System.out.println(feedback.getFeedback());
                        break; 
                    case 4:
                        feedback = farmer.useFertilizer(farmer);
                        System.out.println(feedback.getFeedback());
                        break;
                    case 5:
                        if (farmer.harvestCrop()) {

                        } else {
                            System.out.println("There is nothing to harvest.");
                        }
                        break;
                    case 6:
                        // Check if tile has aa withered crop
                        if (farmer.getFreeTile().isAvailable() == true || farmer.getFreeTile().getCrop().isWithered() == false) {
                            System.out.println("\nNo withered crop to remove!");
                            break;
                        }

                        // Use shovel if the tile has a withered crop
                        farmer.useShovel(farmer);
                        System.out.println("You removed the withered crop!");
                        break;
                    case 7:
                        System.out.println("\nYou slept for 8 hours.");
                        break;
                }
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
                sc.nextLine();

                // clear screen
                System.out.print("\033[H\033[2J");
            }

            if(farm.advanceDay(farmer) == false){
                System.out.println("Game over bro :<");
                exit = true;
            };
            days += 1;
            // ask for user input to continue
            // empty the input buffer

        }
    }
}
