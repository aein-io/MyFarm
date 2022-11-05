import java.util.Scanner;

public class FarmerLevel {
    private String levelTitle;
    private int currentLevel;

    // private int levelRequirement;
    private int bonusEarnings;
    private int seedDiscount;
    private int waterBonusInc;
    private int fertilizerBonusInc;

    private double registrationFee;

    public FarmerLevel() {
        this.currentLevel = 0;
        this.levelTitle = "Farmer";
        this.bonusEarnings = 0;
        this.seedDiscount = 0;
        this.waterBonusInc = 0;
        this.fertilizerBonusInc = 0;
        this.registrationFee = 0.0;
    }
    
    public String getLevelTitle() {
        return levelTitle;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public int getBonusEarnings() {
        return bonusEarnings;
    }
    
    public int getSeedDiscount() {
        return seedDiscount;
    }
    
    public int getWaterBonusInc() {
        return waterBonusInc;
    }
    
    public int getFertilizerBonusInc() {
        return fertilizerBonusInc;
    }
    
    public double getRegistrationFee() {
        return registrationFee;
    }
    
    public void levelUp(Farmer farmer) {    
        Scanner getter = new Scanner(System.in);

        this.currentLevel = (int) farmer.getExp() / 100;

        if(this.currentLevel > 4 && this.levelTitle != "Registered Farmer" &&
                this.levelTitle != "Distinguished Farmer" &&
                this.levelTitle != "Legendary Farmer") {
            this.registrationFee = 200.0;

            // prompt user to pay registration fee
            // if user pays, set levelTitle to "Registered Farmer"


            // check if the farmer can afford the upgrade
            if(farmer.getObjectCoins() < this.registrationFee)
                return;

            System.out.println("You have reached the required level to become a Registered farmer!\nWould you like to pay the registration fee of $200.00? (Y/N)");
            if(getter.nextLine().equalsIgnoreCase("Y")) {
                this.levelTitle = "Registered Farmer";
                System.out.println("You are now a registered farmer!");

                // update farmer level benefits
                this.bonusEarnings = 1;
                this.seedDiscount = 1;
            }
        }

        if(this.currentLevel > 9 && this.levelTitle == "Registered Farmer"){
            this.registrationFee = 300.0;

            // prompt user to pay registration fee
            // if user pays, set levelTitle to "Registered Farmer"

            // check if the farmer can afford the upgrade
            if(farmer.getObjectCoins() < this.registrationFee)
                return;


            System.out.println("You have reached the required level to become a Distinguished farmer!\nWould you like to pay the registration fee of $200.00? (Y/N)");
            if(getter.nextLine().equalsIgnoreCase("Y")) {
                this.levelTitle = "Distinguished Farmer";
                System.out.println("You are now a Distinguished farmer!");

                // update farmer level benefits
                this.bonusEarnings = 2;
                this.seedDiscount = 2;
                this.waterBonusInc = 1;
            }
        }

        if(this.currentLevel > 14 && this.levelTitle == "Distinguished Farmer"){
            this.registrationFee = 400.0;

            // prompt user to pay registration fee
            // if user pays, set levelTitle to "Registered Farmer"

            // check if the farmer can afford the upgrade
            if(farmer.getObjectCoins() < this.registrationFee)
                return;


            System.out.println("You have reached the required level to become a Legendary farmer!\nWould you like to pay the registration fee of $200.00? (Y/N)");
            if(getter.nextLine().equalsIgnoreCase("Y")) {
                this.levelTitle = "Legendary Farmer";
                System.out.println("You are now a Legendary farmer!");

                // update farmer level benefits
                this.bonusEarnings = 3;
                this.seedDiscount = 3;
                this.waterBonusInc = 2;
                this.fertilizerBonusInc = 1;
            }
        }
    }
}
