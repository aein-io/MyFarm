import java.util.*;

public class Tile {

    private Turnip crop;
    private boolean isPlowed;
    private boolean isWithered;
    private boolean isHarvestable;
    private int dayCount;

    public Tile() {
        this.crop = null;
        this.isPlowed = false;
        this.isWithered = false;
        this.isHarvestable = false;
        this.dayCount = 1;
    }

    public String display() {
        
        String tile = "□";

        if(this.isPlowed) {
            tile = "▧";

            if(this.getCrop() != null) {
                if(this.isHarvestable())
                    tile = "♧";
                else if(this.isWithered())
                    tile = "⊠";
                else if(this.getCrop().getTimesWatered() >= 1)
                    tile = "⁕";
                else
                    tile = "🝕";
            }
        }

        return tile;
    }

    public int getDayCount() {
        return this.dayCount;
    }

    public void growCrop() {
        
        Scanner sc = new Scanner(System.in);
        
        this.dayCount = this.dayCount + 1;
        
        // No crop is planted
        if (getCrop() == null) {
            System.out.println("\n< Daily Progress >");
            System.out.println("There are no seeds planted.");
            
            System.out.print("\nPress <ENTER> to continue ");
            sc.nextLine();
        }
        
        // Crop progress
        else {
            System.out.println("\n< Daily Progress >");
            System.out.println("Times Watered: " + getCrop().getTimesWatered());
            System.out.println("Times Fertilized: " + getCrop().getTimesFertilized());
            System.out.println("Crops Harvested: " + getCrop().getProduced());

            // Crop is harvestable
            if (getCrop().getHarvestTime() == getDayCount() &&  (getCrop().getTimesWatered() >= getCrop().getWaterNeeds() || getCrop().getTimesFertilized() >= getCrop().getFertilizerNeeds())) {
                setHarvestable();
                System.out.println("\nGood job! Your crop is ready for harvesting!");
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }

            // Crop has withered
            else if (getCrop().getHarvestTime() == getDayCount() &&  (getCrop().getTimesWatered() < getCrop().getWaterNeeds() || getCrop().getTimesFertilized() < getCrop().getFertilizerNeeds())) {
                setWithered();
                System.out.println("\nOh no! Your crop has withered.");
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }

            // Crop is still growing
            else {
                System.out.print("\nPress <ENTER> to continue ");
                sc.nextLine();
            }
        }
    }

    public void setPlowed() {
        this.isPlowed = true;
    }

    public void setHarvestable() {
        this.isHarvestable = true;
    }

    public void setWithered() {
        this.isWithered= true;
    }

    public Turnip getCrop() {

        return crop;
    }

    public void setCrop(Turnip crop) {
        this.crop = crop;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public boolean isWithered() {
        return isWithered;
    }

}

