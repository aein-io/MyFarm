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

    public int getDayCount() {
    
        return this.dayCount;
    }

    public int advanceDay() {
        
        return this.dayCount = this.dayCount + 1;
    }
    
    public String display() {
        
        String tile = "⧈";

        if(this.isPlowed)
            tile = "▧";

        if(this.crop != null) {
            if(harvestable())
                tile = "♧";
            else if(withered())
                tile = "⊠";
            else
                tile = "⊡"; 
        }

        return tile;
    }

    public boolean plowed() {

        return isPlowed = true;
    }
    
    public boolean withered() {
        
        return isWithered = true;
    }

    public boolean harvestable() {
        
        return isHarvestable = true;
    }

    
}
